package com.devteam.marketing.domain.logs.usr.cash.repository;

import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;

import java.util.List;

public interface UsrCashLogRepositoryCustom {

    List<UsrCashLog> findByUsrIdToDetail(Long usrId);

}
