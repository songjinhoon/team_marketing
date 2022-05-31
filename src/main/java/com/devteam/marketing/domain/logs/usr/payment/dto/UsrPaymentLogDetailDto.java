package com.devteam.marketing.domain.logs.usr.payment.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import com.devteam.marketing.domain.logs.usr.payment.mapper.UsrPaymentLogDtoMapper;
import com.devteam.marketing.domain.usr.dto.UsrDto;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrPaymentLogDetailDto extends BaseDto {

    private Long id;

    private UsrSimpleDto usr;

    private String orderNum;

    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    private Integer occurPay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurFinishTime;

    private String payTool;

    private String description;

    public static UsrPaymentLogDetailDto of(UsrPaymentLog usrPaymentLog) {
        final UsrPaymentLogDetailDto usrPaymentLogDetailDto = UsrPaymentLogDtoMapper.INSTANCE.toUsrPaymentLogDetailDto(usrPaymentLog);
        usrPaymentLogDetailDto.setUsr(UsrSimpleDto.of(usrPaymentLog.getUsr()));
        return usrPaymentLogDetailDto;
    }

    public static List<UsrPaymentLogDetailDto> of(List<UsrPaymentLog> usrPaymentLogs) {
        return usrPaymentLogs.stream().map(UsrPaymentLogDetailDto::of).collect(Collectors.toList());
    }

}
