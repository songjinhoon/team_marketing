package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.dto.UsrDto;
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

    @ApiOperation(value = "전체 유저 조회", notes = "전체 유저를 조회한다")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<?> findAllToSimple() {
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.findAllToSimple()))
                        .build());
    }

    @ApiOperation(value = "유저+동의 조회", notes = "아이디가 일치하는 유저와 동의내역을 함께 조회한다.")
    @GetMapping(value = "/findByIdWithAgree/{id}")
    private ResponseEntity<?> findByIdWithAgree(@PathVariable Long id) {
        final UsrDto.WithAgree usrDto = usrService.findByIdWithAgree(id);
        if (usrDto.getId() == null) {
            return ResponseEntity.ok().body(
                    ResponseDto.builder()
                            .error(true)
                            .message("data not found")
                            .build());
        } else {
            return ResponseEntity.ok().body(
                    ResponseDto.builder()
                            .data(Collections.singletonList(usrDto))
                            .build());
        }
    }

    @ApiOperation(value = "유저 등록", notes = "swagger request dto 일치하지 않음")
    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<?> save(@RequestBody UsrDto.Insert usrDto) {
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.saveWithAgree(usrDto)))
                        .build());
    }

    @ApiOperation(value = "비밀번호 재설정 링크 보내기", notes ="유저의 이메일로 비밀번호 재설정 링크를 보내준다.")
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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UsrDto.Update usrDto) {
        final UsrDto data = usrService.update(id, usrDto);
        if (data instanceof UsrDto.Error) {
            return ResponseEntity.ok().body(
                    ResponseDto.builder()
                            .error(true)
                            .message(((UsrDto.Error) data).getMessage())
                            .build());
        }
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(data))
                        .build());
    }
}
