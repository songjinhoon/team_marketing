package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.dto.UsrInsertDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Usr extends BaseEntity {

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
    private List<UsrAgree> usrAgrees = new ArrayList<>();

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrCash> usrCashes = new ArrayList<>();

    public static Usr create(UsrInsertDto usrInsertDto) {
        return Usr.builder()
                .social(usrInsertDto.getSocial())
                .email(usrInsertDto.getEmail())
                .pwd(usrInsertDto.getPwd())
                .nm(usrInsertDto.getNm())
                .phNum(usrInsertDto.getEmail())
                .useYn(usrInsertDto.getUseYn())
                .cash(usrInsertDto.getCash())
                .build();
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

    public void updateCash(Integer cash) {
        this.cash = cash;

    }

    /*public void addUsrAgrees(UsrAgree usrAgree) {
        usrAgrees.add(usrAgree);
        usrAgree.setUsr(this);
    }*/
}
