package com.devteam.marketing.domain.usr.agree.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDetailDto;
import com.devteam.marketing.domain.usr.agree.service.UsrAgreeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/usr-agree")
@RestController
public class UsrAgreeApi {

    private final UsrAgreeService usrAgreeService;

    @ApiOperation(value = "사용자_동의 조회", notes = "특정 사용자의 사용자_동의를 상세하게 조회한다.")
    @GetMapping(value = "/findByUsrIdToDetail/{usrId}")
    private ResponseEntity<ResponseDto<UsrAgreeDetailDto>> findByUsrIdToDetail(@PathVariable Long usrId) {
        return ResponseEntity.ok().body(ResponseDto.<UsrAgreeDetailDto>builder()
                .data(usrAgreeService.findByUsrIdToDetail(usrId))
                .build());
    }

}
