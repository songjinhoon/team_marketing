package com.devteam.marketing.domain.logs.service;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDetailTimeDto;
import com.devteam.marketing.domain.logs.usr.cash.repository.UsrCashLogRepository;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailTimeDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogInsertDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogUpdateDto;
import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import com.devteam.marketing.domain.logs.usr.payment.repository.UsrPaymentLogRepository;
import com.devteam.marketing.domain.usr.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class LogService {

    private final UsrCashLogRepository usrCashLogRepository;

    private final UsrPaymentLogRepository usrPaymentLogRepository;

    private final UsrRepository usrRepository;

    public List<UsrCashLogDetailTimeDto> usrCashFindByUsrId(Long usrId) {
        return UsrCashLogDetailTimeDto.of(usrCashLogRepository.findByUsrIdToDetail(usrId));
    }

    public List<UsrPaymentLogDetailTimeDto> usrPaymentLogFindByUsrId(Long usrId) {
        return UsrPaymentLogDetailTimeDto.of(usrPaymentLogRepository.findByUsrIdToDetail(usrId));
    }

    public UsrPaymentLogDetailTimeDto usrPaymentSave(UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        usrPaymentLogInsertDto.init(usrRepository.findById(usrPaymentLogInsertDto.getUsrId())
                .orElseThrow(NoSuchElementException::new));
        final UsrPaymentLog usrPaymentLog = usrPaymentLogRepository.save(UsrPaymentLog.create(usrPaymentLogInsertDto));
        return UsrPaymentLogDetailTimeDto.of(usrPaymentLog);
    }

    public UsrPaymentLogDetailTimeDto usrPaymentUpdate(Long id, UsrPaymentLogUpdateDto usrPaymentLogUpdateDto) {
        final UsrPaymentLog usrPaymentLog = usrPaymentLogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("data not found"));
        usrPaymentLog.refundComplete(usrPaymentLogUpdateDto);
        return UsrPaymentLogDetailTimeDto.of(usrPaymentLog);
    }

}
