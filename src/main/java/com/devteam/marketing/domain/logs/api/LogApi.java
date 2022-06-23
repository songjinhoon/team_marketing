package com.devteam.marketing.domain.logs.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.logs.service.LogService;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDetailTimeDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailTimeDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogInsertDto;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogUpdateDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping(value = "/log")
@RestController
public class LogApi {

    private final LogService logService;

    @ApiOperation(value = "사용자_캐쉬_로그 조회", notes = "특정 사용자_캐쉬_로그 데이터 리스트를 조회한다.")
    @GetMapping(value = "/usr-cash/findByUsrId/{usrId}")
    public ResponseEntity<ResponseDto> usrCashFindByUsrId(@PathVariable Long usrId) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(logService.usrCashFindByUsrId(usrId))
                .build());
    }

    @ApiOperation(value = "사용자_결제_로그 조회", notes = "특정 사용자_결제_로그 데이터 조회한다.")
    @GetMapping(value = "/usr-payment/findByUsrId/{usrId}")
    private ResponseEntity<ResponseDto> usrPaymentLogFindByUsrId(@PathVariable Long usrId) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(logService.usrPaymentLogFindByUsrId(usrId))
                .build());
    }

    @ApiOperation(value = "사용자_결제_로그 저장", notes = "사용자_결제_로그 데이터를 저장한다.")
    @PostMapping(value = "/usr-payment/save")
    private ResponseEntity<ResponseDto> usrPaymentSave(@RequestBody UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(logService.usrPaymentSave(usrPaymentLogInsertDto)))
                .build());
    }

    @ApiOperation(value = "사용자_결제_로그 수정", notes = "특정 사용자_결제_로그 데이터를 수정한다.(환불요청관련 데이터의 발생유형/발생완료시간 수정해야 할경우 필요한 api)")
    @PutMapping(value = "/usr-payment/update/{id}")
    private ResponseEntity<ResponseDto> usrPaymentUpdate(@PathVariable Long id, @RequestBody UsrPaymentLogUpdateDto usrPaymentLogUpdateDto){
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(logService.usrPaymentUpdate(id, usrPaymentLogUpdateDto)))
                .build());
    }

}
