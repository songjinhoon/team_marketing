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

}



