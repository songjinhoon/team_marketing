package com.devteam.marketing.domain.logs.usr.cash.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrCashLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_cash_log_id")
    private Long id;

    private Long usrId;

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

    public static UsrCashLog create(UsrCashLogDto.Insert usrCashLogDto) {
        return UsrCashLog.builder()
                .usrId(usrCashLogDto.getUsrId())
                .orderNum(usrCashLogDto.getOrderNum())
                .occurType(usrCashLogDto.getOccurType())
                .occurCash(usrCashLogDto.getOccurCash())
                .occurStartTime(usrCashLogDto.getOccurStartTime())
                .occurFinishTime(usrCashLogDto.getOccurFinishTime())
                .sumCash(usrCashLogDto.getSumCash())
                .chargingCash(usrCashLogDto.getChargingCash())
                .savingCash(usrCashLogDto.getSavingCash())
                .description(usrCashLogDto.getDescription())
                .build();
    }

}
