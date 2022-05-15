package com.devteam.marketing.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class ResponseDto<T> {

    private String error;

    private List<T> data;

}
