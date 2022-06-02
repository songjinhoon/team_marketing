package com.devteam.marketing.domain.logs.usr.cash.repository;

import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrCashLogRepository extends JpaRepository<UsrCashLog, Long>, UsrCashLogRepositoryCustom {
}
