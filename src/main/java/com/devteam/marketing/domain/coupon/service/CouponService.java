package com.devteam.marketing.domain.coupon.service;

import com.devteam.marketing.domain.coupon.dto.CouponSimpleDto;
import com.devteam.marketing.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public List<CouponSimpleDto> findAllToSimple() {
        return CouponSimpleDto.of(couponRepository.findAll());
    }

}
