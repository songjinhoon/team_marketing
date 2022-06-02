package com.devteam.marketing.domain.logs.usr.cash.repository;

import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.logs.usr.cash.entity.QUsrCashLog.usrCashLog;
import static com.devteam.marketing.domain.usr.entity.QUsr.usr;

public class UsrCashLogRepositoryCustomImpl implements UsrCashLogRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrCashLogRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<UsrCashLog> findByUsrIdToDetail(Long usrId) {
        return factory.selectFrom(usrCashLog)
                .innerJoin(usrCashLog.usr, usr).fetchJoin()
                .where(usr.id.eq(usrId))
                .fetch();
    }
}
