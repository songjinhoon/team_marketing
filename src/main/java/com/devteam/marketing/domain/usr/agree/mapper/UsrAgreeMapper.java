package com.devteam.marketing.domain.usr.agree.mapper;

import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrAgreeMapper {

    UsrAgreeMapper INSTANCE = Mappers.getMapper(UsrAgreeMapper.class);

    UsrAgreeDto.WithAgree toWithAgree(UsrAgree usrAgree);

}
