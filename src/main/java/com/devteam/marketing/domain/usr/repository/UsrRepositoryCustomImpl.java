package com.devteam.marketing.domain.usr.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class UsrRepositoryCustomImpl implements UsrRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

}
