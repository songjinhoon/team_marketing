package com.devteam.marketing.domain.logs.usr.payment.repository;

import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.logs.usr.payment.entity.QUsrPaymentLog.usrPaymentLog;
import static com.devteam.marketing.domain.usr.root.entity.QUsr.usr;

public class UsrPaymentLogRepositoryCustomImpl implements UsrPaymentLogRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrPaymentLogRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<UsrPaymentLog> findByUsrIdToDetail(Long usrId) {
        return factory.selectFrom(usrPaymentLog)
                .innerJoin(usrPaymentLog.usr, usr).fetchJoin()
                .fetch();
    }
}
