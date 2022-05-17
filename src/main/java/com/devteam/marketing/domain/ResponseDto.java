package com.devteam.marketing.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class ResponseDto<T> {

    @Builder.Default
    private Boolean error = false;

    @Builder.Default
    private List<T> data = new ArrayList<>();

    @Builder.Default
    private String message = "";

}
