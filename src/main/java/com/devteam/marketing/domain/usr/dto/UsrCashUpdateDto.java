package com.devteam.marketing.domain.usr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsrCashUpdateDto {

    @ApiModelProperty(value = "사용_금액", example = "5000", required = true)
    private Integer cash;

}
