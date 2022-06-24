package com.devteam.marketing.domain.usr.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.common.response.ResponseMessage;
import com.devteam.marketing.domain.usr.dto.UsrCashInsertDto;
import com.devteam.marketing.domain.usr.dto.UsrCashUpdateDto;
import com.devteam.marketing.domain.usr.service.UsrCashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"UsrCash"})
@RequiredArgsConstructor
@RequestMapping(value = "/usr-cash")
@RestController
public class UsrCashApi {

    private final UsrCashService usrCashService;

    @ApiOperation(value = "사용자_캐쉬 저장", notes = "캐쉬 충전")
    @PostMapping(value = "/save")
    private ResponseEntity<ResponseDto> save(@RequestBody UsrCashInsertDto usrCashInsertDto) {
        usrCashService.save(usrCashInsertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                .code(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_CREATE.getValue())
                .build());
    }

    @ApiOperation(value = "사용자_캐쉬 수정", notes = "캐쉬 사용(cash 필드에는 사용금액을 명시)")
    @PutMapping(value = "/update/{usrId}")
    private ResponseEntity<ResponseDto> update(@PathVariable Long usrId, @RequestBody UsrCashUpdateDto usrCashUpdateDto) {
        usrCashService.update(usrId, usrCashUpdateDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .message(ResponseMessage.SUCCESS_UPDATE.getValue())
                .build());
    }

}
/*
* - UsrCash는 이 구조에서 @Get @Delete가 필요없는구조.
* - @Get이유: Usr 도메인이 Cash필드를 가지고 있음, 이걸 바꿀라면 Cash 필드를 제거하고 Cash 도메인을 만드는 형태로가는게 좋을듯
* - @Delete이유: 업데이트가 처리함. 사실상 캐쉬삭제의 개념은 없음 아이디가 삭제되는게 아닌이상. 아이디 삭제시
*   자동으로 이 데이터는 삭제됨. 업데이트로 처리하다 남은캐쉬가 0이되면 스케줄러같은걸로 삭제하는게 맞을듯.
* - 이부분피드백점필요
*/



