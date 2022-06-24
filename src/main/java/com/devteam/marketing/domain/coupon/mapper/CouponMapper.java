package com.devteam.marketing.domain.coupon.mapper;

import com.devteam.marketing.domain.coupon.dto.CouponSimpleDto;
import com.devteam.marketing.domain.coupon.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponMapper {

    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    CouponSimpleDto toCouponSimple(Coupon coupon);

}
