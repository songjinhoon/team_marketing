package com.devteam.marketing.domain.coupon.repository;

import com.devteam.marketing.domain.agree.entity.Agree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Agree, Long> {
}
