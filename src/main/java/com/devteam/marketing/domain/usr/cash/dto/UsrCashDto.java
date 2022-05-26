package com.devteam.marketing.domain.usr.cash.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.logs.usr.cash.dto.UsrCashLogDto;
import com.devteam.marketing.domain.usr.cash.entity.CashType;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.mapper.UsrCashMapper;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class UsrCashDto extends BaseDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "CHARGING")
    @Enumerated(EnumType.STRING)
    private CashType cashType;

    @ApiModelProperty(example = "10000")
    private Integer chargingAmount;

    @ApiModelProperty(example = "10000")
    private Integer remainingAmount;

    @ApiModelProperty(example = "2022-12-01 14:22:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryTime;

    @Getter @Setter
    public static class Insert extends UsrCashDto {

        @ApiModelProperty(hidden = true)
        private Long id;

        @ApiModelProperty(example = "1")
        private Long usrId;

    }

    @Getter @Setter
    public static class Update {

        @ApiModelProperty(example = "1")
        private Long usrId;

        @ApiModelProperty(example = "50000")
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

    @Builder @Getter @Setter
    public static class UsrAndUsrCashLog {

        private UsrDto.Simple usr;

        private UsrCashLogDto.Simple usrCashLog;

    }

}
