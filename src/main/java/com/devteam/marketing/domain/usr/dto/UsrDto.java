package com.devteam.marketing.domain.usr.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.devteam.marketing.domain.usr.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrDto extends BaseDto {

    private Long id;

    private String email;

    private String pwd;

    private String nm;

    private String phNum;

    //프로필 이미지

    private Boolean useAt;

    @Getter @Setter
    public static class Simple extends UsrDto {

        public static Simple of(Usr usr) {
            return UsrMapper.INSTANCE.toSimple(usr);
        }

        public static List<Simple> of(List<Usr> usrs) {
            return usrs.stream().map(Simple::of).collect(Collectors.toList());
        }

    }

}
