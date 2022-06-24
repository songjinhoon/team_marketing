package com.devteam.marketing.domain.usr.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.usr.entity.Social;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.devteam.marketing.domain.usr.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrSimpleDto extends BaseTimeDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Social social;

    private String email;

    private String pwd;

    private String nm;

    private String phNum;

    //프로필 이미지

    private Integer cash;

    private Boolean useYn;

    public static UsrSimpleDto of(Usr usr) {
        return UsrMapper.INSTANCE.toSimple(usr);
    }

    public static List<UsrSimpleDto> of(List<Usr> usrs) {
        return usrs.stream().map(UsrSimpleDto::of).collect(Collectors.toList());
    }

}
