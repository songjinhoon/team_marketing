package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.usr.dto.*;
import com.devteam.marketing.domain.usr.service.UsrService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @ApiOperation(value = "모든 사용자 조회", notes = "모든 사용자 데이터를 조회한다.")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<ResponseDto> findAllToSimple() {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(usrService.findAllToSimple())
                .build());
    }

    @ApiOperation(value = "사용자/사용자_동의 저장", notes = "사용자와/사용자_동의 데이터를 저장한다. usrAgrees는 agree 테이블에 존재하는 row 데이터만큼 넣어줘야 한다.")
    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<ResponseDto> saveWithAgree(@RequestBody UsrInsertDto usrInsertDto) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrService.saveWithAgree(usrInsertDto)))
                .build());
    }

    @ApiOperation(value = "비밀번호 재설정 링크 보내기", notes = "유저의 이메일로 비밀번호 재설정 링크를 보내준다.")
    @PostMapping(value = "/resetPwdLink")
    private ResponseEntity<ResponseDto> resetPwdLink(@RequestBody UsrMailDto usrMailDto) {
        usrService.resetPwdLink(usrMailDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .build());
    }

    @ApiOperation(value = "사용자 수정", notes = "사용자 데이터를 수정한다.")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @RequestBody UsrUpdateDto usrUpdateDto) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrService.update(id, usrUpdateDto)))
                .build());
    }

}
