package com.devteam.marketing.domain.usr.cash.service;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.cash.repository.UsrCashLogRepository;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.repository.UsrCashRepository;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UsrCashService {

    private final UsrRepository usrRepository;

    private final UsrCashRepository usrCashRepository;

    private final UsrCashLogRepository usrCashLogRepository;

    private final EntityManager em;

    public UsrCashDto save(UsrCashDto.Insert usrCashDto) {
        final Optional<Usr> optional = usrRepository.findById(usrCashDto.getUsrId());
        if (optional.isEmpty()) {
            return UsrCashDto.Error.builder()
                    .message("data not found error")
                    .build();
        }
        final Usr usr = optional.get();
        final UsrCash usrCash = UsrCash.create(usrCashDto);
        boolean check = usr.addUsrCash(usrCash);
        if (!check) {
            return UsrCashDto.Error.builder()
                    .message("limit excess error")
                    .build();
        }

        em.flush();

        /* 현시점 usr cash 만료 체크 후 재계산 */
        LocalDateTime nowTime = LocalDateTime.now();

        /* 현시점 남은 충전캐쉬 */
        Integer chargingCash = usr.getUsrCashes().stream()
                .filter(data -> data.getCashType().equals(CashType.CHARGING))
                .filter(data -> nowTime.isBefore(data.getExpiryTime()))
                .mapToInt(UsrCash::getRemainingAmount)
                .sum();

        /* 현시점 남은 적립캐쉬 */
        Integer savingCash = usr.getUsrCashes().stream()
                .filter(data -> data.getCashType().equals(CashType.SAVING))
                .filter(data -> nowTime.isBefore(data.getExpiryTime()))
                .mapToInt(UsrCash::getRemainingAmount)
                .sum();
        Integer sumCash = chargingCash + savingCash;
        usr.updateCash(sumCash);

        /* USR_CASH_LOG SAVE */
        final UsrCashLog usrCashLog = UsrCashLog.create(UsrCashLogDto.Insert.builder()
                .usrId(usrCashDto.getUsrId())
                .orderNum("" + usrCashDto.getUsrId() + usrCash.getId() + System.currentTimeMillis())
                .occurType(usrCashDto.getCashType().equals(CashType.CHARGING) ? OccurType.CHARGING_COMPLETE : OccurType.SAVING_COMPLETE)
                .occurCash(usrCashDto.getChargingAmount())
                .occurStartTime(usrCash.getRgsDt())
                .occurFinishTime(usrCash.getRgsDt()) // 환불같은거는 처리기간 고려해야될듯?
                .sumCash(sumCash)
                .chargingCash(chargingCash)
                .savingCash(savingCash)
                .description("캐쉬구매")
                .build());

        usrCashLogRepository.save(usrCashLog);

        return UsrCashDto.Detail.of(usrCash);
    }

    public UsrCashDto update(UsrCashDto.Update usrCashDto) {
        final Optional<Usr> optional = usrRepository.findById(usrCashDto.getUsrId());
        if (optional.isEmpty()) {
            return UsrCashDto.Error.builder()
                    .message("data not found error")
                    .build();
        }

        final Usr usr = optional.get();
        if (usr.getCash() < usrCashDto.getCash()) {
            return UsrCashDto.Error.builder()
                    .message("cash lack error") // 잔액 부족
                    .build();
        }

        final LocalDateTime nowTime = LocalDateTime.now();
        final List<UsrCash> usrCashes = usr.getUsrCashes().stream()
                .filter(data -> nowTime.isBefore(data.getExpiryTime()))
                .collect(Collectors.toList());
        final List<UsrCash> chargingUsrCash = usrCashes.stream()
                .filter(data -> data.getCashType().equals(CashType.CHARGING))
                .sorted(Comparator.comparing(UsrCash::getExpiryTime)) // 만료일 가까운순 정렬
                .collect(Collectors.toList());
        Integer sum = 0;
        boolean check = false;
        for (int i=0; i<chargingUsrCash.size(); i++) {
            Integer prevSum = sum;
            sum += chargingUsrCash.get(i).getRemainingAmount();
            if (sum < usrCashDto.getCash()) { // 10000원을 사용해야한다면
                chargingUsrCash.get(i).updateRemainingAmount(0);
            } else {
                check = true;
                usr.updateCash(usr.getCash() - usrCashDto.getCash());
                if (sum.equals(usrCashDto.getCash())) {
                    chargingUsrCash.get(i).updateRemainingAmount(0);
                } else { // 10000원을 사용해야하는데 sum이 12000원이 되버림 마지막섬이 9000원이야
                    Integer needCash = usrCashDto.getCash() - prevSum;
                    chargingUsrCash.get(i).updateRemainingAmount(chargingUsrCash.get(i).getRemainingAmount() - needCash);
                }
            }
        }
        if (check) { // 충전캐쉬가 부족해서 적립캐쉬활용할경우
            final List<UsrCash> savingUsrCash = usrCashes.stream()
                    .filter(data -> data.getCashType().equals(CashType.SAVING))
                    .sorted(Comparator.comparing(UsrCash::getExpiryTime)) // 만료일 가까운순 정렬
                    .collect(Collectors.toList());
            for (int i=0; i<savingUsrCash.size(); i++) {
                Integer prevSum = sum;
                sum += savingUsrCash.get(i).getRemainingAmount();
                if (sum < usrCashDto.getCash()) {
                    savingUsrCash.get(i).updateRemainingAmount(0);
                } else {
                    usr.updateCash(usr.getCash() - usrCashDto.getCash());
                    if (sum.equals(usrCashDto.getCash())) {
                        savingUsrCash.get(i).updateRemainingAmount(0);
                    } else {
                        Integer needCash = usrCashDto.getCash() - prevSum;
                        savingUsrCash.get(i).updateRemainingAmount(savingUsrCash.get(i).getRemainingAmount() - needCash);
                    }
                }
            }
        }

        /* 캐쉬 사용관련 로그 저장 */



        return null;
    }

}
