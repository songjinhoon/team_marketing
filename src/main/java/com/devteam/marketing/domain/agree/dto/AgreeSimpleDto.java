package com.devteam.marketing.domain.agree.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.mapper.AgreeMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgreeSimpleDto extends BaseTimeDto {

    private Long id;

    private String text;

    private Boolean useYn;

    public static AgreeSimpleDto of(Agree agree) {
        return AgreeMapper.INSTANCE.toAgreeSimple(agree);
    }

    public static List<AgreeSimpleDto> of(List<Agree> agrees) {
        return agrees.stream().map(AgreeSimpleDto::of).collect(Collectors.toList());
    }

}
