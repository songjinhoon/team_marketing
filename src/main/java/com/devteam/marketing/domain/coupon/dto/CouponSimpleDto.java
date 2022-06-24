package com.devteam.marketing.domain.coupon.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.coupon.entity.Coupon;
import com.devteam.marketing.domain.coupon.mapper.CouponMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class CouponSimpleDto extends BaseTimeDto {

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
