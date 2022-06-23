package com.devteam.marketing.domain.usr.mapper;

import com.devteam.marketing.domain.usr.dto.UsrSimpleTimeDto;
import com.devteam.marketing.domain.usr.entity.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrMapper {

    UsrMapper INSTANCE = Mappers.getMapper(UsrMapper.class);

    UsrSimpleTimeDto toSimple(Usr usr);

}
