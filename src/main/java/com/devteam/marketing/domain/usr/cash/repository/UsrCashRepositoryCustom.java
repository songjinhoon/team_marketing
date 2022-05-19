package com.devteam.marketing.domain.usr.cash.repository;

import com.devteam.marketing.domain.usr.cash.entity.UsrCash;

import java.util.List;

public interface UsrCashRepositoryCustom {

    List<UsrCash> findDetailByUsrId(Long usrId);

}
