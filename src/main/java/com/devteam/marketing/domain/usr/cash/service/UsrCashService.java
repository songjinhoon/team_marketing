package com.devteam.marketing.domain.usr.cash.service;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.cash.repository.UsrCashLogRepository;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.repository.UsrCashRepository;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
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
        if (usr.getCash() + usrCashDto.getChargingAmount() > 2000000) {
            return UsrCashDto.Error.builder()
                    .message("limit excess error")
                    .build();
        }

        final LocalDateTime nowTime = LocalDateTime.now();
        /* 캐쉬 충전 API 추가 Process 시작 */

        /* 캐쉬 충전 API 추가 Process 종료*/
        final UsrCash usrCash = UsrCash.create(usrCashDto);
        usr.addUsrCash(usrCash);
        em.flush();

        final UsrCashLogDto.Simple usrCashLogDto = this.usrCashUpdate(usrCashDto.getUsrId(), nowTime, usrCashDto.getCashType().equals(CashType.CHARGING) ? OccurType.CHARGING_COMPLETE : OccurType.SAVING_COMPLETE, usrCashDto.getChargingAmount());

        return UsrCashDto.Detail.of(usrCash);
    }

    public Object update(UsrCashDto.Update usrCashDto) {
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
        boolean check = true;
        for (UsrCash usrCash : chargingUsrCash) {
            Integer prevSum = sum;
            sum += usrCash.getRemainingAmount();
            if (sum < usrCashDto.getCash()) { // 10000원을 사용해야한다면
                usrCash.updateRemainingAmount(0);
            } else {
                check = false;
                if (sum.equals(usrCashDto.getCash())) {
                    usrCash.updateRemainingAmount(0);
                } else { // 10000원을 사용해야하는데 sum이 12000원이 되버림 마지막섬이 9000원이야
                    Integer needCash = usrCashDto.getCash() - prevSum;
                    usrCash.updateRemainingAmount(usrCash.getRemainingAmount() - needCash);
                }
                break;
            }
        }
        if (check) { // 충전캐쉬가 부족해서 적립캐쉬활용할경우
            final List<UsrCash> savingUsrCash = usrCashes.stream()
                    .filter(data -> data.getCashType().equals(CashType.SAVING))
                    .sorted(Comparator.comparing(UsrCash::getExpiryTime))
                    .collect(Collectors.toList());
            for (UsrCash usrCash : savingUsrCash) {
                Integer prevSum = sum;
                sum += usrCash.getRemainingAmount();
                if (sum < usrCashDto.getCash()) {
                    usrCash.updateRemainingAmount(0);
                } else {
                    if (sum.equals(usrCashDto.getCash())) {
                        usrCash.updateRemainingAmount(0);
                    } else {
                        Integer needCash = usrCashDto.getCash() - prevSum;
                        usrCash.updateRemainingAmount(usrCash.getRemainingAmount() - needCash);
                    }
                    break;
                }
            }
        }
        em.flush();
        final UsrCashLogDto.Simple usrCashLogDto = this.usrCashUpdate(usrCashDto.getUsrId(), nowTime, OccurType.USE_COMPLETE, usrCashDto.getCash());

        return UsrCashDto.UsrAndUsrCashLog.builder()
                .usr(UsrDto.Simple.of(usr))
                .usrCashLog(usrCashLogDto)
                .build();
    }

    public UsrCashLogDto.Simple usrCashUpdate(Long usrId, LocalDateTime nowTime, OccurType occurType, Integer cash) {
        final List<UsrCash> usrCashes = usrCashRepository.findDetailByUsrId(usrId);
        /* 현시점 남은 충전캐쉬 */
        final Integer chargingCash = usrCashes.stream()
                .filter(data -> data.getCashType().equals(CashType.CHARGING))
                .filter(data -> nowTime.isBefore(data.getExpiryTime()))
                .mapToInt(UsrCash::getRemainingAmount)
                .sum();
        /* 현시점 남은 적립캐쉬 */
        final Integer savingCash = usrCashes.stream()
                .filter(data -> data.getCashType().equals(CashType.SAVING))
                .filter(data -> nowTime.isBefore(data.getExpiryTime()))
                .mapToInt(UsrCash::getRemainingAmount)
                .sum();
        /* 현시점 종합캐쉬 */
        final Integer sumCash = chargingCash + savingCash;

        /* Usr 반영 */
        usrCashes.get(0).getUsr().updateCash(sumCash);

        /* UsrCashLog 반영 */
        final UsrCashLog usrCashLog = usrCashLogRepository.save(UsrCashLog.create(UsrCashLogDto.Insert.builder()
                .usrId(usrId)
                .orderNum("" + usrId + System.currentTimeMillis())
                .occurType(occurType)
                .occurCash(cash)
                .occurStartTime(nowTime)
                .occurFinishTime(nowTime) // 환불같은거는 처리기간 고려해야될듯?
                .sumCash(sumCash)
                .chargingCash(chargingCash)
                .savingCash(savingCash)
                .description(occurType.getValue())
                .build()));
        return UsrCashLogDto.Simple.of(usrCashLog);
    }

}
