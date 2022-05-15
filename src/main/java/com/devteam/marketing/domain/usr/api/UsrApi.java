package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.service.UsrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<?> findAllToSimple() {
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.findAllToSimple()))
                        .build());
    }


}
