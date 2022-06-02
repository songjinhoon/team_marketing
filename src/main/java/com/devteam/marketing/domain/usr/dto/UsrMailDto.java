package com.devteam.marketing.domain.usr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsrMailDto {

    @ApiModelProperty(value = "보낼이메일", example = "hijinhoon@naver.com", required = true)
    private String email;

    @ApiModelProperty(value = "redirect 주소", example = "http://www.naver.com", required = true)
    private String link;

}
