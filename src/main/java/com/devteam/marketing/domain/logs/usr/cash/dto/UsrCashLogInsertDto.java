package com.devteam.marketing.domain.logs.usr.cash.dto;

import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class UsrCashLogInsertDto {

    @ApiModelProperty(value = "사용자_아이디", example = "1", required = true)
    private Long id;

    @ApiModelProperty(hidden = true)
    private String orderNum;

    @ApiModelProperty(value = "발생_형태", example = "CHARGING_COMPLETE", required = true)
    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    @ApiModelProperty(value = "발생_금액", example = "10000", required = true)
    private Integer occurCash;

    @ApiModelProperty(value = "발생_시작_시간", example = "2022-05-30 12:42:33", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurStartTime;

    @ApiModelProperty(value = "발생_완료_시간(사용취소일 경우 'null' 보내고 추후에 완료시간 업데이트)", example = "2022-05-30 12:42:33")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurFinishTime;

    @ApiModelProperty(value = "종합_캐쉬", example = "10000", required = true)
    private Integer sumCash;

    @ApiModelProperty(value = "충전_캐쉬", example = "10000", required = true)
    private Integer chargingCash;

    @ApiModelProperty(value = "적립_캐쉬", example = "0", required = true)
    private Integer savingCash;

    @ApiModelProperty(value = "비고", example = "비고란")
    private String description;

    @ApiModelProperty(hidden = true)
    private Usr usr;

    public void init(Usr usr) {
        this.orderNum = "" + usr.getId() + System.currentTimeMillis();
        this.usr = usr;
    }

}
