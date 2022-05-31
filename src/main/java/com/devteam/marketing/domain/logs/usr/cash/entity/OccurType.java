package com.devteam.marketing.domain.logs.usr.cash.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OccurType {

    CHARGING_COMPLETE("충전완료"),
    SAVING_COMPLETE("적립완료"),
    USE_COMPLETE("사용완료"),
    USE_CANCEL("사용취소"),
    PAYMENT_COMPLETE("결제완료"),
    REFUND_COMPLETE("환불완료"),
    REFUND_REQUEST("환불요청");

    private final String value;

}
