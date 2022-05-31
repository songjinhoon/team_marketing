package com.devteam.marketing.domain.logs.usr.payment.repository;

import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrPaymentLogRepository extends JpaRepository<UsrPaymentLog, Long>, UsrPaymentLogRepositoryCustom {
}
