package com.devteam.marketing.domain.agree.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.agree.service.AgreeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping(value = "/agree")
@RestController
public class AgreeApi {

    private final AgreeService agreeService;

    @ApiOperation(value = "전체 동의 조회", notes = "전체 동의 목록을 조회한다")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<?> findAllToSimple() {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(agreeService.findAllToSimple()))
                .build());
    }

}
