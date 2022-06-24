package com.devteam.marketing.domain.usr.repository;

import com.devteam.marketing.domain.usr.entity.UsrCash;

import java.util.List;

public interface UsrCashRepositoryCustom {

    List<UsrCash> findDetailByUsrId(Long usrId);

}
