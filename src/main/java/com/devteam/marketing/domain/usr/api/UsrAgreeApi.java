package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.usr.service.UsrAgreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"UsrAgree"})
@RequiredArgsConstructor
@RequestMapping("/usr-agree")
@RestController
public class UsrAgreeApi {

    private final UsrAgreeService usrAgreeService;

    @ApiOperation(value = "사용자_동의 조회", notes = "사용자와 동의 상세조회")
    @GetMapping(value = "/findByUsrIdToDetail/{usrId}")
    private ResponseEntity<ResponseDto> findByUsrIdToDetail(@PathVariable Long usrId) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(usrAgreeService.findByUsrIdToDetail(usrId))
                .build());
    }

}
