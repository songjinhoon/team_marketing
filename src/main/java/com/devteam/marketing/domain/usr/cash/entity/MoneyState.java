package com.devteam.marketing.domain.usr.cash.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoneyState {

    CHARGING_COMPLETE("CHARGING_COMPLETE", "충전완료"),
    USE_COMPLETE("USE_COMPLETEM", "사용완료"),
    USE_CANCEL("USE_CANCEL", "사용취소"),
    PAYMENT_COMPLETE("PAYMENT_COMPLETE", "결재완료"),
    REFUND_COMPLETE("REFUND_COMPLETE", "환불완료");

    private final String key;

    private final String value;

}
