package com.devteam.marketing.domain.agree.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.mapper.AgreeMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgreeDto extends BaseDto {

    private Long id;

    private String text;

    private Boolean useYn;

    @Getter @Setter
    public static class Simple extends AgreeDto {

        public static Simple of(Agree agree) {
            return AgreeMapper.INSTANCE.toSimple(agree);
        }

        public static List<Simple> of(List<Agree> agrees) {
            return agrees.stream().map(Simple::of).collect(Collectors.toList());
        }

    }

}
