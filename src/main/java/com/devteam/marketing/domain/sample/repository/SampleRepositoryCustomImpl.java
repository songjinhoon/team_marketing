package com.devteam.marketing.domain.sample.repository;

import com.devteam.marketing.domain.sample.entity.Sample;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.sample.entity.QSample.sample;

public class SampleRepositoryCustomImpl implements SampleRepositoryCustom {

    private final JPAQueryFactory factory;

    public SampleRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<Sample> findAllTest() {
        return factory.selectFrom(sample).fetch();
    }
}
