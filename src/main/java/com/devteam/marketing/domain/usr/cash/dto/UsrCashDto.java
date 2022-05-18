package com.devteam.marketing.domain.usr.cash.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.mapper.UsrCashMapper;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrCashDto extends BaseDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private CashType cashType;

    private Integer chargingAmount;

    private Integer remainingAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryTime;

    @Getter @Setter
    public static class Insert extends UsrCashDto {

        private Long usrId;

    }

    @Getter @Setter
    public static class Update {

        private Long usrId;

        private Integer cash;

    }

    @Getter @Builder
    public static class Error extends UsrCashDto {

        private String message;

    }

    @Getter @Setter
    public static class Detail extends UsrCashDto {

        private UsrDto.Simple usr;

        public static Detail of(UsrCash usrCash) {
            final Detail detail = UsrCashMapper.INSTANCE.toDetail(usrCash);
            detail.setUsr(UsrDto.Simple.of(usrCash.getUsr()));
            return detail;
        }

    }




}
