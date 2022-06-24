package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrCash extends BaseTimeEntity {

    @Id
    @Column(name = "usr_cash_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "usr_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usr usr;

    @Enumerated(EnumType.STRING)
    private CashType cashType;

    private Integer chargingAmount;

    private Integer remainingAmount;

    private LocalDateTime expiryTime; // 계산을 프론트에서 해줄지 백에서 해줄지 정의 필요

    @Builder
    public UsrCash(Usr usr, CashType cashType, Integer chargingAmount, Integer remainingAmount, LocalDateTime expiryTime) {
        this.usr = usr;
        this.cashType = cashType;
        this.chargingAmount = chargingAmount;
        this.remainingAmount = remainingAmount;
        this.expiryTime = expiryTime;
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }

    public void updateRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

}
