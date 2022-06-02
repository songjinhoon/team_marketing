package com.devteam.marketing.domain.usr.dto;

import com.devteam.marketing.domain.usr.entity.Social;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter @Setter
public class UsrInsertDto {

    private List<UsrAgreeLinkDto> usrAgrees;

    @ApiModelProperty(value = "소셜", example = "NONE", required = true)
    @Enumerated(EnumType.STRING)
    private Social social;

    @ApiModelProperty(value = "이메일", example = "hijinhoon@naver.com", required = true)
    private String email;

    @ApiModelProperty(value = "패스워드(Social값이 NONE일 경우 필요하고 아니면 null)", example = "admin")
    private String pwd;

    @ApiModelProperty(value = "이름", example = "관리자", required = true)
    private String nm;

    @ApiModelProperty(value = "핸드폰_번호", example = "01050188147", required = true)
    private String phNum;

    @ApiModelProperty(value = "캐쉬", example = "0", required = true)
    private Integer cash;

    @ApiModelProperty(value = "사용_여부", example = "true", required = true)
    private Boolean useYn;

}
