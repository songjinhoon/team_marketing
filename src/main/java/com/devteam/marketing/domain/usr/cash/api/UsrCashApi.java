package com.devteam.marketing.domain.usr.cash.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
import com.devteam.marketing.domain.usr.cash.service.UsrCashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping(value = "/usr-cash")
@RestController
public class UsrCashApi {

    private final UsrCashService usrCashService;

    /* 충전캐쉬, 적립캐쉬 저장 */
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

    /* 충전캐쉬, 적립캐쉬 사용
    *  충전캐쉬 사용후 부족한 부분 적립캐쉬로 사용
    * */
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



