package com.devteam.marketing.domain.agree.mapper;

import com.devteam.marketing.domain.agree.dto.AgreeDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgreeMapper {

    AgreeMapper INSTANCE = Mappers.getMapper(AgreeMapper.class);

    AgreeDto.Simple toSimple(Agree agree);

}
