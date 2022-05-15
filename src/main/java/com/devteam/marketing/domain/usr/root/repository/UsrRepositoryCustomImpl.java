package com.devteam.marketing.domain.usr.root.repository;

import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.devteam.marketing.domain.agree.entity.QAgree.agree;
import static com.devteam.marketing.domain.usr.agree.entity.QUsrAgree.usrAgree;
import static com.devteam.marketing.domain.usr.root.entity.QUsr.usr;

public class UsrRepositoryCustomImpl implements UsrRepositoryCustom {

    private final JPAQueryFactory factory;

    public UsrRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

}
