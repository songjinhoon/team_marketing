package com.devteam.marketing.domain.logs.usr.cash.mapper;

import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDto;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrCashLogMapper {

    UsrCashLogMapper INSTANCE = Mappers.getMapper(UsrCashLogMapper.class);

    UsrCashLogDto.Simple toSimple(UsrCashLog usrCashLog);

}
