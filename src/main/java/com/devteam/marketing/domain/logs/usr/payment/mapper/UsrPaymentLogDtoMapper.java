package com.devteam.marketing.domain.logs.usr.payment.mapper;

import com.devteam.marketing.domain.logs.usr.payment.dto.UsrPaymentLogDetailDto;
import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsrPaymentLogDtoMapper {

    UsrPaymentLogDtoMapper INSTANCE = Mappers.getMapper(UsrPaymentLogDtoMapper.class);

    UsrPaymentLogDetailDto toUsrPaymentLogDetailDto(UsrPaymentLog usrPaymentLog);

}
