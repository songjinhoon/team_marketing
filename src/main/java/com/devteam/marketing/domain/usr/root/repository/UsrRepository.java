package com.devteam.marketing.domain.usr.root.repository;

import com.devteam.marketing.domain.usr.root.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<Usr, Long>, UsrRepositoryCustom {
}
