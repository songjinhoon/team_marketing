package com.devteam.marketing.domain.usr.cash.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CashType {

    CHARGING("CASH_CHARGING", "충전"),
    SAVING("CASH_SAVING", "적립");

    private final String key;

    private final String value;


}
