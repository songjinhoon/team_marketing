package com.devteam.marketing.domain.usr.agree.dto;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.usr.entity.Usr;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UsrAgreeInsertDto {

    private Usr usr;

    private Agree agree;

    private Boolean agreeYn;

}
