package com.devteam.marketing.domain.logs.usr.cash.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogInsertDto;
import com.devteam.marketing.domain.usr.entity.Usr;
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

    @JoinColumn(name = "usr_id")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public static UsrCashLog create(UsrCashLogInsertDto usrCashLogInsertDto) {
        return UsrCashLog.builder()
                .usr(usrCashLogInsertDto.getUsr())
                .orderNum(usrCashLogInsertDto.getOrderNum())
                .occurType(usrCashLogInsertDto.getOccurType())
                .occurCash(usrCashLogInsertDto.getOccurCash())
                .occurStartTime(usrCashLogInsertDto.getOccurStartTime())
                .occurFinishTime(usrCashLogInsertDto.getOccurFinishTime())
                .sumCash(usrCashLogInsertDto.getSumCash())
                .chargingCash(usrCashLogInsertDto.getChargingCash())
                .savingCash(usrCashLogInsertDto.getSavingCash())
                .description(usrCashLogInsertDto.getDescription())
                .build();
    }

}
