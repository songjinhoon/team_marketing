package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.UsrAgree;

import java.util.List;

public interface UsrAgreeRepositoryCustom {

    List<UsrAgree> findByUsrIdToDetail(Long usrId);

}
