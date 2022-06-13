package com.devteam.marketing.domain.coupon.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.coupon.entity.Coupon;
import com.devteam.marketing.domain.coupon.mapper.CouponMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class CouponSimpleDto extends BaseDto {

    private Long id;

    private String text;

    private Boolean useYn;

    public static CouponSimpleDto of(Coupon coupon) {
        return CouponMapper.INSTANCE.toCouponSimple(coupon);
    }

    public static List<CouponSimpleDto> of(List<Coupon> coupons) {
        return coupons.stream().map(CouponSimpleDto::of).collect(Collectors.toList());
    }
}
