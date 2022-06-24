package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.UsrAgree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrAgreeRepository extends JpaRepository<UsrAgree, Long>, UsrAgreeRepositoryCustom {
}
