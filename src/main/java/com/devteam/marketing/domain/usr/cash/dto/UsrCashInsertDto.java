package com.devteam.marketing.domain.usr.cash.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrCashInsertDto extends BaseDto {

    @ApiModelProperty(value = "사용자_아이디", example = "1", required = true)
    private Long usrId;

    @ApiModelProperty(value = "캐쉬_형태", example = "CHARGING", required = true)
    @Enumerated(EnumType.STRING)
    private CashType cashType;

    @ApiModelProperty(value = "충전_금액", example = "10000", required = true)
    private Integer chargingAmount;

    @ApiModelProperty(value = "잔여_금액", example = "10000", required = true)
    private Integer remainingAmount;

    @ApiModelProperty(value = "만료_시간", example = "2022-12-01 14:22:30", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryTime;

    @ApiModelProperty(hidden = true)
    private Usr usr;

    public void init(Usr usr) {
        this.usr = usr;
    }

}
