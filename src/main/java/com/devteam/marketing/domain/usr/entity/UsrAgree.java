package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.agree.entity.Agree;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class UsrAgree extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_agree_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    private Usr usr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agree_id")
    private Agree agree;

    private Boolean agreeYn;

    @Builder
    public UsrAgree(Usr usr, Agree agree, Boolean agreeYn) {
        this.usr = usr;
        this.agree = agree;
        this.agreeYn = agreeYn;
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }

    public void setAgree(Agree agree) {
        this.agree = agree;
    }

}
