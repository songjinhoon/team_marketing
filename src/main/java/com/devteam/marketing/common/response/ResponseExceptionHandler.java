package com.devteam.marketing.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> exception(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR.getValue())
                .build());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ResponseDto> notFoundException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ResponseMessage.ERROR_NOT_FOUND.getValue())
                .build());
    }

}
