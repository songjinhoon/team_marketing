package com.devteam.marketing.domain.coupon.mapper;

import com.devteam.marketing.domain.agree.dto.AgreeSimpleDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponMapper {

    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    AgreeSimpleDto toAgreeSimple(Agree agree);

}
