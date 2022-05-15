package com.devteam.marketing.domain.usr.agree.repository;

import com.devteam.marketing.domain.agree.entity.QAgree;
import com.devteam.marketing.domain.usr.agree.entity.QUsrAgree;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.root.entity.QUsr;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.agree.entity.QAgree.agree;
import static com.devteam.marketing.domain.usr.agree.entity.QUsrAgree.usrAgree;
import static com.devteam.marketing.domain.usr.root.entity.QUsr.usr;

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
