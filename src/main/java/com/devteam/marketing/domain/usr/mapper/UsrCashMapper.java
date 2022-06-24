package com.devteam.marketing.domain.usr.mapper;

import com.devteam.marketing.domain.usr.dto.UsrCashDetailDto;
import com.devteam.marketing.domain.usr.entity.UsrCash;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrCashMapper {

    UsrCashMapper INSTANCE = Mappers.getMapper(UsrCashMapper.class);

    UsrCashDetailDto toDetail(UsrCash usrCash);

}
