package com.devteam.marketing.domain.usr.agree.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.agree.dto.AgreeSimpleTimeDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.mapper.UsrAgreeMapper;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrAgreeDetailTimeDto extends BaseTimeDto {

    private Long id;

    private UsrSimpleDto usr;

    private AgreeSimpleTimeDto agree;

    private Boolean agreeYn;

    public static UsrAgreeDetailTimeDto of(UsrAgree usrAgree) {
        final UsrAgreeDetailTimeDto usrAgreeDetailDto = UsrAgreeMapper.INSTANCE.toDetail(usrAgree);
        usrAgreeDetailDto.setUsr(UsrSimpleDto.of(usrAgree.getUsr()));
        usrAgreeDetailDto.setAgree(AgreeSimpleTimeDto.of(usrAgree.getAgree()));
        return usrAgreeDetailDto;
    }

    public static List<UsrAgreeDetailTimeDto> of(List<UsrAgree> usrAgrees) {
        return usrAgrees.stream().map(UsrAgreeDetailTimeDto::of).collect(Collectors.toList());
    }

}
