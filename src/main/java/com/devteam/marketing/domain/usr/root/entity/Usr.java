package com.devteam.marketing.domain.usr.root.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
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

    private Boolean useYn;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrAgree> usrAgrees = new ArrayList<>();

    public static Usr create(UsrDto.Insert usrDto) {
        return Usr.builder()
                .social(usrDto.getSocial())
                .email(usrDto.getEmail())
                .pwd(usrDto.getSocial().equals(Social.NONE) ? null : usrDto.getPwd())
                .nm(usrDto.getNm())
                .phNum(usrDto.getPhNum())
                .useYn(usrDto.getUseYn())
                .build();
    }

    public static Usr empty() {
        return Usr.builder().build();
    }

    /*public void addUsrAgrees(UsrAgree usrAgree) {
        usrAgrees.add(usrAgree);
        usrAgree.setUsr(this);
    }*/
}
