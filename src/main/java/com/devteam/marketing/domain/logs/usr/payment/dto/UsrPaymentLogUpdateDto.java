package com.devteam.marketing.domain.logs.usr.payment.dto;

import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrPaymentLogUpdateDto {

    @ApiModelProperty(value = "발생_유형", example = "REFUND_COMPLETE", required = true)
    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    @ApiModelProperty(value = "발생_종료_시간", example = "2022-12-24 12:44:58", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurFinishTime;

}
