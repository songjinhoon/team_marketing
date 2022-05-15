package com.devteam.marketing.domain.usr.root.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.root.entity.Social;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.devteam.marketing.domain.usr.root.mapper.UsrMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrDto extends BaseDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Social social;

    private String email;

    private String pwd;

    private String nm;

    private String phNum;

    //프로필 이미지

    private Boolean useYn;

    @Getter @Setter
    public static class Insert extends UsrDto {

        private List<UsrAgreeDto.Insert> usrAgrees;

    }

    @Getter @Setter
    public static class Simple extends UsrDto {

        public static Simple of(Usr usr) {
            return UsrMapper.INSTANCE.toSimple(usr);
        }

        public static List<Simple> of(List<Usr> usrs) {
            return usrs.stream().map(Simple::of).collect(Collectors.toList());
        }

    }

    @Getter @Setter
    public static class WithAgree extends UsrDto {

        private List<UsrAgreeDto.WithAgree> usrAgrees;

        public static WithAgree of(Usr usr, List<UsrAgree> usrAgrees) {
            final WithAgree withAgree = UsrMapper.INSTANCE.toWithAgree(usr);
            withAgree.setUsrAgrees(UsrAgreeDto.WithAgree.of(usrAgrees));
            return withAgree;
        }

    }

}
