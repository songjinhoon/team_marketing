package com.devteam.marketing.domain.agree.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.mapper.AgreeMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class AgreeDto extends BaseDto {

    @ApiModelProperty(example = "아이디")
    private Long id;

    @ApiModelProperty(example = "내용")
    private String text;

    @ApiModelProperty(example = "사용여부")
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
