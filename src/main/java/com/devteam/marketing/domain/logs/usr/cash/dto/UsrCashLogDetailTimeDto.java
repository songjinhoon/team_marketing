package com.devteam.marketing.domain.logs.usr.cash.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.cash.mapper.UsrCashLogMapper;
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
public class UsrCashLogDetailTimeDto extends BaseTimeDto {

    private Long id;

    private UsrSimpleDto usr;

    private String orderNum;

    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    private Integer occurCash;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurFinishTime;

    private Integer sumCash;

    private Integer chargingCash;

    private Integer savingCash;

    private String description;

    public static UsrCashLogDetailTimeDto of(UsrCashLog usrCashLog) {
        final UsrCashLogDetailTimeDto usrCashLogDetailDto = UsrCashLogMapper.INSTANCE.toDetail(usrCashLog);
        usrCashLogDetailDto.setUsr(UsrSimpleDto.of(usrCashLog.getUsr()));
        return usrCashLogDetailDto;
    }

    public static List<UsrCashLogDetailTimeDto> of(List<UsrCashLog> usrCashLogs) {
        return usrCashLogs.stream().map(UsrCashLogDetailTimeDto::of).collect(Collectors.toList());
    }

}
