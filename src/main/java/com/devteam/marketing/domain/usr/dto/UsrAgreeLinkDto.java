package com.devteam.marketing.domain.usr.dto;

import com.devteam.marketing.domain.usr.entity.UsrAgree;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsrAgreeLinkDto {

    @ApiModelProperty(value = "동의_아이디", example = "1", required = true)
    private Long agreeId;

    @ApiModelProperty(value = "동의_여부", example = "true", required = true)
    private Boolean agreeYn;

    public UsrAgree toEntity() {
        return UsrAgree.builder()
                .agreeYn(agreeYn)
                .build();
    }

}
