package com.devteam.marketing.domain.logs.usr.cash.dto;

import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.usr.entity.Usr;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class UsrCashLogInsertDto {

    private Usr usr;

    private String orderNum;

    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    private Integer occurCash;

    private LocalDateTime occurStartTime;

    private LocalDateTime occurFinishTime;

    private Integer sumCash;

    private Integer chargingCash;

    private Integer savingCash;

    private String description;

    public UsrCashLog toEntity() {
        return UsrCashLog.builder()
                .usr(this.usr)
                .orderNum(this.orderNum)
                .occurType(this.occurType)
                .occurCash(this.occurCash)
                .occurStartTime(this.occurStartTime)
                .occurFinishTime(this.occurFinishTime)
                .sumCash(this.sumCash)
                .chargingCash(this.chargingCash)
                .savingCash(this.savingCash)
                .description(this.description)
                .build();
    }

}
