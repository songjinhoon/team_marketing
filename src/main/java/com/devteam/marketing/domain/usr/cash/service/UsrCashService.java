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
import java.util.Optional;

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

}
