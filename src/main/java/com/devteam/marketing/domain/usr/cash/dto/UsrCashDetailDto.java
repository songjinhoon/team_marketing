package com.devteam.marketing.domain.usr.cash.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.mapper.UsrCashMapper;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrCashDetailDto extends BaseDto {

    private Long id;

    private UsrSimpleDto usr;

    @Enumerated(EnumType.STRING)
    private CashType cashType;

    private Integer chargingAmount;

    private Integer remainingAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryTime;

    public static UsrCashDetailDto of(UsrCash usrCash) {
        final UsrCashDetailDto usrCashDetailDto = UsrCashMapper.INSTANCE.toDetail(usrCash);
        usrCashDetailDto.setUsr(UsrSimpleDto.of(usrCash.getUsr()));
        return usrCashDetailDto;
    }

}
