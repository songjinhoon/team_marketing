package com.devteam.marketing.domain.coupon.dto;

import com.devteam.marketing.domain.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CouponSimpleDto extends BaseDto {

    private Long id;

    private String text;

    private Boolean useYn;


}
