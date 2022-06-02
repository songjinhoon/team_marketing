package com.devteam.marketing.domain.usr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsrUpdateDto {

    @ApiModelProperty(value = "이름", example = "닉네임1234", required = true)
    private String nm;

    @ApiModelProperty(value = "이전_비밀번호", example = "admin123", required = true)
    private String prevPwd;

    @ApiModelProperty(value = "다음_비밀번호", example = "admin123456", required = true)
    private String nextPwd;

}
