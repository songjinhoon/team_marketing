package com.devteam.marketing.domain.usr.cash.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrCash extends BaseEntity {

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

    public static UsrCash create(UsrCashDto.Insert usrMoneyDto) {
        return UsrCash.builder()
                .cashType(usrMoneyDto.getCashType())
                .chargingAmount(usrMoneyDto.getChargingAmount())
                .remainingAmount(usrMoneyDto.getRemainingAmount())
                .expiryTime(usrMoneyDto.getExpiryTime())
                .build();
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }

    public void updateRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

}
