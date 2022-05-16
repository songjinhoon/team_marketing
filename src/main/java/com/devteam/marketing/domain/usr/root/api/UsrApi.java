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
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.findByIdWithAgree(id)))
                        .build());
    }

    @PostMapping(value = "/saveWithAgree")
    private ResponseEntity<?> save(@RequestBody UsrDto.Insert usrDto) {
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .data(Collections.singletonList(usrService.saveWithAgree(usrDto)))
                        .build());
    }

    @PostMapping(value = "/findPwd")
    private ResponseEntity<?> findPwd(@RequestBody UsrDto.Mail usrDto) {
        final String returnValue = usrService.findPwd(usrDto);
        return ResponseEntity.ok().body(
                ResponseDto.builder()
                        .error(!returnValue.equals("success"))
                        .text(returnValue.equals("fail") ? "내부 시스템 오류" : returnValue)
                        .build());
    }


}
