package com.devteam.marketing.domain.usr.mapper;

import com.devteam.marketing.domain.usr.dto.UsrDto;
import com.devteam.marketing.domain.usr.entity.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrMapper {

    UsrMapper INSTANCE = Mappers.getMapper(UsrMapper.class);

    UsrDto.Simple toSimple(Usr usr);

    @Mapping(target = "usrAgrees", ignore = true)
    UsrDto.WithAgree toWithAgree(Usr usr);

}
