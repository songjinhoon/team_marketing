package com.devteam.marketing.domain.usr.root.api;

import com.devteam.marketing.domain.ResponseDto;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.devteam.marketing.domain.usr.root.service.UsrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /* 회원가입 */
    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<?> save(@RequestBody UsrDto.Insert usrDto) {
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.saveWithAgree(usrDto)))
                        .build());
    }

    /* 비밀번호 재설정링크 --> 작업은 했지만 잘못된거같음
    *  이유: 백단에서는 email 존재 여부를 체크 후 반환만하고 메일전송은 프론트에서 해야 rest api인듯
    * */
    @PostMapping(value = "/resetPwdLink")
    private ResponseEntity<?> resetPwdLink(@RequestBody UsrDto.Mail usrDto) {
        final String returnValue = usrService.resetPwdLink(usrDto);
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .error(!returnValue.equals("success"))
                        .message(returnValue.equals("fail") ? "내부 시스템 오류" : returnValue)
                        .build());
    }

    /* 업데이트 
    *  추후 비밀번호랑 닉네임등 업데이트 화면에따라 기능좀바꿔야함
    * */
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
