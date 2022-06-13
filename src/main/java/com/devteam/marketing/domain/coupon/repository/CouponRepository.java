package com.devteam.marketing.domain.coupon.repository;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
