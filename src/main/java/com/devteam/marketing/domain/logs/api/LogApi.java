package com.devteam.marketing.domain.logs.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.logs.service.LogService;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogInsertDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/log")
@RestController
public class LogApi {

    private final LogService logService;

    @ApiOperation(value = "사용자 결제 로그 조회", notes = "특정 사용자의 결제 로그 조회한다.")
    @GetMapping(value = "/usr/payment/findByUsrId/{usrId}")
    private ResponseEntity<ResponseDto<UsrPaymentLogDetailDto>> usrPaymentLogFindByUsrId(@PathVariable Long usrId) {
        final List<UsrPaymentLogDetailDto> usrPaymentLogDetailDtos = logService.usrPaymentLogFindByUsrId(usrId);
        return ResponseEntity.ok().body(ResponseDto.<UsrPaymentLogDetailDto>builder()
                .data(usrPaymentLogDetailDtos)
                .build());
    }

    @ApiOperation(value = "사용자 결제 로그 저장", notes = "사용자 결제 로그 데이터를 저장한다.")
    @PostMapping(value = "/usr/payment/save")
    private ResponseEntity<ResponseDto<UsrPaymentLogDetailDto>> save(@RequestBody UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        final UsrPaymentLogDetailDto usrPaymentLogDetailDto = logService.usrPaymentSave(usrPaymentLogInsertDto);
        return ResponseEntity.ok().body(ResponseDto.<UsrPaymentLogDetailDto>builder()
                .data(Collections.singletonList(usrPaymentLogDetailDto))
                .build());
    }

}
