package com.devteam.marketing.domain.usr.cash.mapper;

import com.devteam.marketing.domain.usr.cash.dto.UsrCashDetailTimeDto;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrCashMapper {

    UsrCashMapper INSTANCE = Mappers.getMapper(UsrCashMapper.class);

    UsrCashDetailTimeDto toDetail(UsrCash usrCash);

}
