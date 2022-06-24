package com.devteam.marketing.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@Builder
public class ResponseDto {

    @Builder.Default
    private int code = HttpStatus.OK.value();

    @Builder.Default
    private String message = ResponseMessage.SUCCESS_READ.getValue();

    @Builder.Default
    private Object data = new HashMap<>();

}
