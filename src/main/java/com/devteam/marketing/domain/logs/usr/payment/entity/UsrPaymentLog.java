package com.devteam.marketing.domain.logs.usr.payment.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.logs.usr.cash.entity.OccurType;
import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogInsertDto;
import com.devteam.marketing.domain.usr.entity.Usr;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrPaymentLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_payment_log_id")
    private Long id;

    @JoinColumn(name = "usr_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usr usr;

    private String orderNum;

    @Enumerated(EnumType.STRING)
    private OccurType occurType;

    private Integer occurPay;

    private LocalDateTime occurStartTime;

    private LocalDateTime occurFinishTime;

    private String payTool;

    private String description;

    public static UsrPaymentLog create(UsrPaymentLogInsertDto usrPaymentLogInsertDto) {
        return UsrPaymentLog.builder()
                .usr(usrPaymentLogInsertDto.getUsr())
                .orderNum(usrPaymentLogInsertDto.getOrderNum())
                .occurType(usrPaymentLogInsertDto.getOccurType())
                .occurPay(usrPaymentLogInsertDto.getOccurPay())
                .occurStartTime(usrPaymentLogInsertDto.getOccurStartTime())
                .occurFinishTime(usrPaymentLogInsertDto.getOccurFinishTime())
                .payTool(usrPaymentLogInsertDto.getPayTool())
                .description(usrPaymentLogInsertDto.getDescription())
                .build();
    }

}
