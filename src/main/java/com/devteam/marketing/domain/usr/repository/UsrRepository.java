package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<Usr, Long> {
}
