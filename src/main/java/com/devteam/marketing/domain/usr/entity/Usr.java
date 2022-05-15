package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

    private Boolean useAt;

}
