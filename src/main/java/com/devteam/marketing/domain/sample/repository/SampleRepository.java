package com.devteam.marketing.domain.sample.repository;

import com.devteam.marketing.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom {
}
