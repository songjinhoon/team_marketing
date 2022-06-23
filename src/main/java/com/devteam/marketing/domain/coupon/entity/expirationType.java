package com.devteam.marketing.domain.coupon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum expirationType {
    PERIOD("기간"),
    daysFromIssuance("발급후 n일");
    private final String value;
}
