package com.devteam.marketing.domain.agree.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.agree.dto.AgreeSimpleTimeDto;
import com.devteam.marketing.domain.agree.service.AgreeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/agree")
@RestController
public class AgreeApi {

    private final AgreeService agreeService;

    @ApiOperation(value = "전체 동의 조회", notes = "전체 동의 목록을 조회한다")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<ResponseDto> findAllToSimple() {
        return ResponseEntity.ok().body(ResponseDto.<AgreeSimpleTimeDto>builder()
                .data(agreeService.findAllToSimple())
                .build());
    }

}
