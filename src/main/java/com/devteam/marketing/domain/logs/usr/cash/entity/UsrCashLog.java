package com.devteam.marketing.domain.logs.usr.cash.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.usr.entity.Usr;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrCashLog extends BaseTimeEntity {

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

    @Builder
    public UsrCashLog(Usr usr, String orderNum, OccurType occurType, Integer occurCash, LocalDateTime occurStartTime, LocalDateTime occurFinishTime, Integer sumCash, Integer chargingCash, Integer savingCash, String description) {
        this.usr = usr;
        this.orderNum = orderNum;
        this.occurType = occurType;
        this.occurCash = occurCash;
        this.occurStartTime = occurStartTime;
        this.occurFinishTime = occurFinishTime;
        this.sumCash = sumCash;
        this.chargingCash = chargingCash;
        this.savingCash = savingCash;
        this.description = description;
    }

}
