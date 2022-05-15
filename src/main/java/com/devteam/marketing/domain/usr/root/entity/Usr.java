package com.devteam.marketing.domain.usr.root.entity;

import com.devteam.marketing.domain.BaseEntity;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
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

    private String email;

    private String pwd;

    private String nm;

    private String phNum;

    //프로필 이미지

    private Boolean useYn;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrAgree> usrAgrees = new ArrayList<>();



}
