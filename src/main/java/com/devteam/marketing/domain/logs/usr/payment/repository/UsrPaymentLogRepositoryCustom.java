package com.devteam.marketing.domain.logs.usr.payment.repository;

import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;

import java.util.List;

public interface UsrPaymentLogRepositoryCustom {

    List<UsrPaymentLog> findByUsrIdToDetail(Long usrId);

}
