package com.devteam.marketing.domain.usr.agree.repository;

import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;

import java.util.List;

public interface UsrAgreeRepositoryCustom {

    List<UsrAgree> findByUsrIdToDetail(Long usrId);

}
