package com.devteam.marketing.domain.usr.agree.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.agree.dto.AgreeSimpleDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.mapper.UsrAgreeMapper;
import com.devteam.marketing.domain.usr.entity.Usr;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrAgreeDto extends BaseDto {

    @ApiModelProperty(example = "유저_동의_아이디")
    private Long id;

    @ApiModelProperty(example = "동의_여부")
    private Boolean agreeYn;

    @Getter @Setter
    public static class Insert extends UsrAgreeDto {

        @ApiModelProperty(example = "동의_아이디")
        private Long agreeId;

    }

    @Getter @Setter
    public static class Save extends UsrAgreeDto {

        private Usr usr;

        private Agree agree;

        @Builder
        public Save(Boolean agreeYn, Usr usr, Agree agree) {
            super.agreeYn = agreeYn;
            this.usr = usr;
            this.agree = agree;
        }

    }

}
