package com.devteam.marketing.domain.usr.agree.repository;

import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrAgreeRepository extends JpaRepository<UsrAgree, Long>, UsrAgreeRepositoryCustom {
}
