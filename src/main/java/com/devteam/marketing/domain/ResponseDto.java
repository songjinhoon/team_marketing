package com.devteam.marketing.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class ResponseDto<T> {

    @Builder.Default
    private int status = HttpStatus.OK.value();

    @Builder.Default
    private String message = "success";

    @Builder.Default
    private List<T> data = new ArrayList<>();

    @Builder.Default
    private Boolean error = false;

}
