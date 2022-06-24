package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.UsrAgree;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.agree.entity.QAgree.agree;
import static com.devteam.marketing.domain.usr.entity.QUsr.usr;
import static com.devteam.marketing.domain.usr.entity.QUsrAgree.usrAgree;

public class UsrAgreeRepositoryCustomImpl implements UsrAgreeRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrAgreeRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<UsrAgree> findByUsrIdToDetail(Long usrId) {
        return factory.selectFrom(usrAgree)
                .innerJoin(usrAgree.agree, agree).fetchJoin()
                .innerJoin(usrAgree.usr, usr).fetchJoin()
                .where(usrAgree.usr.id.eq(usrId))
                .fetch();
    }
}
