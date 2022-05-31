package com.devteam.marketing.domain.logs.service;

import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogInsertDto;
import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import com.devteam.marketing.domain.logs.usr.payment.repository.UsrPaymentLogRepository;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class LogService {

    private final UsrPaymentLogRepository usrPaymentLogRepository;

    private final UsrRepository usrRepository;

    public UsrPaymentLogDetailDto usrPaymentSave(UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        usrPaymentLogInsertDto.init(usrRepository.findById(usrPaymentLogInsertDto.getUsrId())
                .orElseThrow(NoSuchElementException::new));
        final UsrPaymentLog usrPaymentLog = usrPaymentLogRepository.save(UsrPaymentLog.create(usrPaymentLogInsertDto));
        return UsrPaymentLogDetailDto.of(usrPaymentLog);
    }

    public List<UsrPaymentLogDetailDto> usrPaymentLogFindByUsrId(Long usrId) {
        final List<UsrPaymentLog> usrPaymentLogs = usrPaymentLogRepository.findByUsrIdToDetail(usrId);
        return UsrPaymentLogDetailDto.of(usrPaymentLogs);
    }
}
