package com.devteam.marketing.domain.agree.dto;

import com.devteam.marketing.common.dto.BaseTimeDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.mapper.AgreeMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgreeSimpleTimeDto extends BaseTimeDto {

    private Long id;

    private String text;

    private Boolean useYn;

    public static AgreeSimpleTimeDto of(Agree agree) {
        return AgreeMapper.INSTANCE.toAgreeSimple(agree);
    }

    public static List<AgreeSimpleTimeDto> of(List<Agree> agrees) {
        return agrees.stream().map(AgreeSimpleTimeDto::of).collect(Collectors.toList());
    }

}
