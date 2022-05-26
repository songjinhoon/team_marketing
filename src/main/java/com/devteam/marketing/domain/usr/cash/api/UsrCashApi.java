package com.devteam.marketing.domain.usr.cash.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
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

    @ApiOperation(value = "유저캐쉬 등록", notes = "유저가 가질 캐쉬를 등록한다.")
    @PostMapping(value = "/save")
    private ResponseEntity<?> save(@RequestBody UsrCashDto.Insert usrCashDto) {
        final UsrCashDto save = usrCashService.save(usrCashDto);
        if (save instanceof UsrCashDto.Error) {
            return ResponseEntity.ok().body(
                    ResponseDto.builder()
                            .error(true)
                            .message(((UsrCashDto.Error) save).getMessage())
                            .build());
        }
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(save))
                        .build());
    }

    @ApiOperation(value = "유저캐쉬 수정", notes = "유저가 가지고있는 캐쉬를 수정한다. cash 필드에는 사용할 캐쉬 금액을 입력한다. 충전캐쉬 선 사용 후 부족한 부분 적립캐쉬로 사용한다.")
    @PutMapping(value = "/update")
    private ResponseEntity<?> update(@RequestBody UsrCashDto.Update usrCashDto) {
        final Object update = usrCashService.update(usrCashDto);
        if (update instanceof UsrCashDto.Error) {
            return ResponseEntity.ok().body(
                    ResponseDto.builder()
                            .error(true)
                            .message(((UsrCashDto.Error) update).getMessage())
                            .build());
        }
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(update))
                        .build());
    }

}



