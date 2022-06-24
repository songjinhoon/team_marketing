package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.common.response.ResponseMessage;
import com.devteam.marketing.domain.usr.dto.*;
import com.devteam.marketing.domain.usr.service.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Usr"})
@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @ApiOperation(value = "사용자/사용자_동의 저장", notes = "회원가입(usrAgrees는 agree 테이블에 존재하는 row 데이터만큼 필요)")
    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<ResponseDto> save(@RequestBody UsrInsertDto usrInsertDto) {
        usrService.save(usrInsertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                .code(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_CREATE.getValue())
                .build());
    }

    @ApiOperation(value = "단일 사용자 조회", notes = "회원정보 조회")
    @GetMapping(value = "/findByIdToSimple/{id}")
    public ResponseEntity<ResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(usrService.findByIdToSimple(id))
                .build());
    }

    @ApiOperation(value = "모든 사용자 조회", notes = "회원목록")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<ResponseDto> get() {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(usrService.findAllToSimple())
                .build());
    }
    
    /*
    * - 사용자 조회 페이징처리 개발
    * */

    @ApiOperation(value = "사용자 수정", notes = "회원정보 수정")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @RequestBody UsrUpdateDto usrUpdateDto) {
        usrService.update(id, usrUpdateDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .message(ResponseMessage.SUCCESS_UPDATE.getValue())
                .build());
    }

    @ApiOperation(value = "사용자 삭제", notes = "회원탈퇴")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        usrService.delete(id);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .message(ResponseMessage.SUCCESS_DELETE.getValue())
                .build());
    }

    @ApiOperation(value = "비밀번호 재설정 링크 메일 발신", notes = "비밀번호 재설정 링크")
    @PostMapping(value = "/resetPwdLink")
    private ResponseEntity<ResponseDto> resetPwdLink(@RequestBody UsrMailDto usrMailDto) {
        usrService.resetPwdLink(usrMailDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .message("")
                .build());
    }

}
