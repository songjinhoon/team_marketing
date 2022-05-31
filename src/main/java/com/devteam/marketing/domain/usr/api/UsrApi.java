package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.dto.UsrDto;
import com.devteam.marketing.domain.usr.dto.UsrInsertDto;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
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
    public ResponseEntity<ResponseDto<UsrSimpleDto>> findAllToSimple() {
        return ResponseEntity.ok().body(ResponseDto.<UsrSimpleDto>builder()
                .data(usrService.findAllToSimple())
                .build());
    }

    @ApiOperation(value = "사용자/사용자_동의 저장", notes = "사용자와/사용자_동의 데이터를 저장한다.")
    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<ResponseDto<UsrSimpleDto>> saveWithAgree(@RequestBody UsrInsertDto usrInsertDto) {
        return ResponseEntity.ok().body(ResponseDto.<UsrSimpleDto>builder()
                .data(Collections.singletonList(usrService.saveWithAgree(usrInsertDto)))
                .build());
    }

    @ApiOperation(value = "비밀번호 재설정 링크 보내기", notes = "유저의 이메일로 비밀번호 재설정 링크를 보내준다.")
    @PostMapping(value = "/resetPwdLink")
    private ResponseEntity<?> resetPwdLink(@RequestBody UsrDto.Mail usrDto) {
        final String returnValue = usrService.resetPwdLink(usrDto);
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .error(!returnValue.equals("success"))
                        .message(returnValue.equals("fail") ? "내부 시스템 오류" : returnValue)
                        .build());
    }

    @ApiOperation(value = "유저 데이터 수정", notes = "swagger request dto 일치하지 않음")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto<UsrSimpleDto>> update(@PathVariable Long id, @RequestBody UsrDto.Update usrDto) {
        return ResponseEntity.ok().body(ResponseDto.<UsrSimpleDto>builder()
                .data(Collections.singletonList(usrService.update(id, usrDto)))
                .build());
    }
}
