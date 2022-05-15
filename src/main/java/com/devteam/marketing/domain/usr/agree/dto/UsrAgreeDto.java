package com.devteam.marketing.domain.usr.agree.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.agree.dto.AgreeDto;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.mapper.UsrAgreeMapper;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UsrAgreeDto extends BaseDto {

    private Long id;

    private Boolean agreeYn;

    @Getter @Setter
    public static class Insert extends UsrAgreeDto {

        private Usr usr;

        private Agree agree;

        private Long agreeId;

    }

    @Getter @Setter
    public static class WithAgree extends UsrAgreeDto {

        private Long usrId;

        private AgreeDto.Simple agree;

        public static WithAgree of(UsrAgree usrAgree) {
            final WithAgree withAgree = UsrAgreeMapper.INSTANCE.toWithAgree(usrAgree);
            withAgree.setUsrId(usrAgree.getUsr().getId());
            withAgree.setAgree(AgreeDto.Simple.of(usrAgree.getAgree()));
            return withAgree;
        }

        public static List<WithAgree> of(List<UsrAgree> usrAgrees) {
            return usrAgrees.stream().map(WithAgree::of).collect(Collectors.toList());
        }

    }

}
