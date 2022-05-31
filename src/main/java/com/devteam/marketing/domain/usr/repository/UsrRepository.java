package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsrRepository extends JpaRepository<Usr, Long>, UsrRepositoryCustom {

    Optional<Usr> findByEmail(String email);

}
