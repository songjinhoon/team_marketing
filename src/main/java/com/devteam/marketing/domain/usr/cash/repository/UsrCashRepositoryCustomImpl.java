package com.devteam.marketing.domain.usr.cash.repository;

import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.usr.cash.entity.QUsrCash.usrCash;
import static com.devteam.marketing.domain.usr.entity.QUsr.usr;

public class UsrCashRepositoryCustomImpl implements UsrCashRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrCashRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<UsrCash> findDetailByUsrId(Long usrId) {
        return factory.selectFrom(usrCash)
                .innerJoin(usrCash.usr, usr).fetchJoin()
                .where(usr.id.eq(usrId))
                .fetch();
    }
}
