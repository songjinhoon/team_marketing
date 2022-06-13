package com.devteam.marketing.domain.coupon.entity;

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
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String text;

    private Boolean useYn;

    @OneToMany(mappedBy = "agree", cascade = CascadeType.ALL)
    private List<UsrAgree> usrAgrees = new ArrayList<>();

}
