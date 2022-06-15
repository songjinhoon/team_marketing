package com.devteam.marketing.domain.coupon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum discountType {
    FIXED("고정비율"),
    PERCENT("퍼센트비율");

    private final String value;
}
