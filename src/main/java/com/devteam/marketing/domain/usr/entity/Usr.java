package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Usr extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
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

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrAgree> usrAgrees;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrCash> usrCashes;

    @Builder
    public Usr(Social social, String email, String pwd, String nm, String phNum, Integer cash, Boolean useYn) {
        this.social = social;
        this.email = email;
        this.pwd = pwd;
        this.nm = nm;
        this.phNum = phNum;
        this.cash = cash;
        this.useYn = useYn;
        this.usrCashes = new ArrayList<>();
        this.usrAgrees = new ArrayList<>();
    }

    public void updatePwd (String pwd) {
        this.pwd = pwd;
    }

    public void updateNm (String nm) {
        this.nm = nm;
    }

    public void addUsrCash(UsrCash usrCash) {
        usrCashes.add(usrCash);
        usrCash.setUsr(this);
    }

    public void addUsrAgree(UsrAgree usrAgree) {
        usrAgrees.add(usrAgree);
        usrAgree.setUsr(this);
    }

    public void updateCash(Integer cash) {
        this.cash = cash;
    }

}
