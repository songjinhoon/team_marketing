package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.UsrCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrCashRepository extends JpaRepository<UsrCash, Long>, UsrCashRepositoryCustom {
}
