package com.devteam.marketing.domain.usr.cash.service;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDetailDto;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogInsertDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.cash.repository.UsrCashLogRepository;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDetailDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashInsertDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashUpdateDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.repository.UsrCashRepository;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.devteam.marketing.domain.usr.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UsrCashService {

    private final UsrRepository usrRepository;

    private final UsrCashRepository usrCashRepository;

    private final UsrCashLogRepository usrCashLogRepository;

    private final EntityManager em;

    public UsrCashDetailDto save(UsrCashInsertDto usrCashInsertDto) {
        final Usr usr = usrRepository.findById(usrCashInsertDto.getUsrId()).orElseThrow(() -> new NoSuchElementException("data not found"));
        if (usr.getCash() + usrCashInsertDto.getChargingAmount() > 2000000) {
            throw new RuntimeException("limit excess");
        }

        usrCashInsertDto.init(usr);
        final LocalDateTime nowTime = LocalDateTime.now();
        final UsrCash usrCash = UsrCash.create(usrCashInsertDto);
        usr.addUsrCash(usrCash);
        em.flush();

        final UsrCashLogDetailDto usrCashLogDetailDto = this.usrCashUpdate(usrCashInsertDto.getUsrId(), nowTime, usrCashInsertDto.getCashType().equals(CashType.CHARGING) ? OccurType.CHARGING_COMPLETE : OccurType.SAVING_COMPLETE, usrCashInsertDto.getChargingAmount());
        return UsrCashDetailDto.of(usrCash);
    }

    public UsrCashLogDetailDto update(Long usrId, UsrCashUpdateDto usrCashUpdateDto) {
        final Usr usr = usrRepository.findById(usrId).orElseThrow(() -> new NoSuchElementException("data not found"));
        if (usr.getCash() < usrCashUpdateDto.getCash()) {
            throw new RuntimeException("cash lack");
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
            if (sum < usrCashUpdateDto.getCash()) { // 10000원을 사용해야한다면
                usrCash.updateRemainingAmount(0);
            } else {
                check = false;
                if (sum.equals(usrCashUpdateDto.getCash())) {
                    usrCash.updateRemainingAmount(0);
                } else { // 10000원을 사용해야하는데 sum이 12000원이 되버림 마지막섬이 9000원이야
                    Integer needCash = usrCashUpdateDto.getCash() - prevSum;
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
                if (sum < usrCashUpdateDto.getCash()) {
                    usrCash.updateRemainingAmount(0);
                } else {
                    if (sum.equals(usrCashUpdateDto.getCash())) {
                        usrCash.updateRemainingAmount(0);
                    } else {
                        Integer needCash = usrCashUpdateDto.getCash() - prevSum;
                        usrCash.updateRemainingAmount(usrCash.getRemainingAmount() - needCash);
                    }
                    break;
                }
            }
        }
        em.flush();

        return this.usrCashUpdate(usrId, nowTime, OccurType.USE_COMPLETE, usrCashUpdateDto.getCash());
    }

    public UsrCashLogDetailDto usrCashUpdate(Long usrId, LocalDateTime nowTime, OccurType occurType, Integer cash) {
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

        final UsrCashLogInsertDto usrCashLogInsertDto = UsrCashLogInsertDto.builder()
                .occurType(occurType)
                .occurCash(cash)
                .occurStartTime(nowTime)
                .occurFinishTime(nowTime)
                .sumCash(sumCash)
                .chargingCash(chargingCash)
                .savingCash(savingCash)
                .description(occurType.getValue())
                .build();
        usrCashLogInsertDto.init(usrCashes.get(0).getUsr());

        return UsrCashLogDetailDto.of(usrCashLogRepository.save(UsrCashLog.create(usrCashLogInsertDto)));
    }

}
