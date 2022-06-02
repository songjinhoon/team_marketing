package com.devteam.marketing.domain.logs.service;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDetailDto;
import com.devteam.marketing.domain.logs.usr.cash.repository.UsrCashLogRepository;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailDto;
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

    public List<UsrCashLogDetailDto> usrCashFindByUsrId(Long usrId) {
        return UsrCashLogDetailDto.of(usrCashLogRepository.findByUsrIdToDetail(usrId));
    }

    public List<UsrPaymentLogDetailDto> usrPaymentLogFindByUsrId(Long usrId) {
        return UsrPaymentLogDetailDto.of(usrPaymentLogRepository.findByUsrIdToDetail(usrId));
    }

    public UsrPaymentLogDetailDto usrPaymentSave(UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        usrPaymentLogInsertDto.init(usrRepository.findById(usrPaymentLogInsertDto.getUsrId())
                .orElseThrow(NoSuchElementException::new));
        final UsrPaymentLog usrPaymentLog = usrPaymentLogRepository.save(UsrPaymentLog.create(usrPaymentLogInsertDto));
        return UsrPaymentLogDetailDto.of(usrPaymentLog);
    }

    public UsrPaymentLogDetailDto usrPaymentUpdate(Long id, UsrPaymentLogUpdateDto usrPaymentLogUpdateDto) {
        final UsrPaymentLog usrPaymentLog = usrPaymentLogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("data not found"));
        usrPaymentLog.refundComplete(usrPaymentLogUpdateDto);
        return UsrPaymentLogDetailDto.of(usrPaymentLog);
    }

}
