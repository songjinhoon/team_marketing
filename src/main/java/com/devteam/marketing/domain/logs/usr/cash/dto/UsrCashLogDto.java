package com.devteam.marketing.domain.logs.usr.cash.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.cash.mapper.UsrCashLogMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrCashLogDto extends BaseDto {

    private Long id;

    private Long usrId;

    private String orderNum; /* 규칙이 필요할듯 */

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

    public static class Insert extends UsrCashLogDto {

        @Builder
        public Insert(Long id, Long usrId, String orderNum, OccurType occurType, Integer occurCash, LocalDateTime occurStartTime, LocalDateTime occurFinishTime, Integer sumCash, Integer chargingCash, Integer savingCash, String description) {
            super.id = id;
            super.usrId = usrId;
            super.orderNum = orderNum;
            super.occurType = occurType;
            super.occurCash = occurCash;
            super.occurStartTime = occurStartTime;
            super.occurFinishTime = occurFinishTime;
            super.sumCash = sumCash;
            super.chargingCash = chargingCash;
            super.savingCash = savingCash;
            super.description = description;
        }
        
    }

    public static class Simple extends UsrCashLogDto {

        public static Simple of(UsrCashLog usrCashLog) {
            return UsrCashLogMapper.INSTANCE.toSimple(usrCashLog);
        }

    }

}