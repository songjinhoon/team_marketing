package com.devteam.marketing.domain.agree.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Agree extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agree_id")
    private Long id;

    private String text;

    private Boolean useYn;

    @OneToMany(mappedBy = "agree", cascade = CascadeType.ALL)
    private List<UsrAgree> usrAgrees = new ArrayList<>();

    public void addUsrAgree(UsrAgree usrAgree) {
        usrAgrees.add(usrAgree);
        usrAgree.setAgree(this);
    }

}
