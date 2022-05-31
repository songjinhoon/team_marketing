package com.devteam.marketing.domain.usr.agree.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.agree.dto.AgreeSimpleDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.mapper.UsrAgreeMapper;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrAgreeDetailDto extends BaseDto {

    private Long id;

    private UsrSimpleDto usr;

    private AgreeSimpleDto agree;

    private Boolean agreeYn;

    public static UsrAgreeDetailDto of(UsrAgree usrAgree) {
        final UsrAgreeDetailDto usrAgreeDetailDto = UsrAgreeMapper.INSTANCE.toDetail(usrAgree);
        usrAgreeDetailDto.setUsr(UsrSimpleDto.of(usrAgree.getUsr()));
        usrAgreeDetailDto.setAgree(AgreeSimpleDto.of(usrAgree.getAgree()));
        return usrAgreeDetailDto;
    }

    public static List<UsrAgreeDetailDto> of(List<UsrAgree> usrAgrees) {
        return usrAgrees.stream().map(UsrAgreeDetailDto::of).collect(Collectors.toList());
    }

}
