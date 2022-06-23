package com.devteam.marketing.domain.usr.cash.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDetailTimeDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDetailTimeDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashInsertTimeDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashUpdateDto;
import com.devteam.marketing.domain.usr.cash.service.UsrCashService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping(value = "/usr-cash")
@RestController
public class UsrCashApi {

    private final UsrCashService usrCashService;

    @ApiOperation(value = "사용자_캐쉬 저장", notes = "사용자의 (충전/적립)캐쉬 데이터를 저장한다.")
    @PostMapping(value = "/save")
    private ResponseEntity<ResponseDto> save(@RequestBody UsrCashInsertTimeDto usrCashInsertDto) {
        final UsrCashDetailTimeDto usrCashDetailDto = usrCashService.save(usrCashInsertDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrCashDetailDto))
                .build());
    }

    @ApiOperation(value = "유저캐쉬 수정", notes = "사용자가 캐쉬를 사용했을경우 캐쉬데이터를 반영한다. cash 필드에는 사용금액을 명시한다.")
    @PutMapping(value = "/update/{usrId}")
    private ResponseEntity<ResponseDto> update(@PathVariable Long usrId, @RequestBody UsrCashUpdateDto usrCashUpdateDto) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrCashService.update(usrId, usrCashUpdateDto)))
                .build());
    }

}



