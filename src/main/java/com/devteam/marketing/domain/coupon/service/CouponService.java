package com.devteam.marketing.domain.coupon.service;

import com.devteam.marketing.domain.agree.dto.AgreeSimpleDto;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final AgreeRepository agreeRepository;

    public List<AgreeSimpleDto> findAllToSimple() {
        return AgreeSimpleDto.of(agreeRepository.findAll());
    }

}
