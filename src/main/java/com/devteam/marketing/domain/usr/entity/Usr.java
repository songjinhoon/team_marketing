package com.devteam.marketing.domain.usr.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.logs.usr.cash.entity.UsrCashLog;
import com.devteam.marketing.domain.logs.usr.payment.entity.UsrPaymentLog;
import com.devteam.marketing.domain.usr.dto.UsrUpdateDto;
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

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrCashLog> usrCashLogs;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<UsrPaymentLog> usrPaymentLogs;

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
        this.usrCashLogs = new ArrayList<>();
        this.usrPaymentLogs = new ArrayList<>();
    }

    public void update(UsrUpdateDto usrUpdateDto) {
        this.nm = usrUpdateDto.getNm();
        this.pwd = usrUpdateDto.getNextPwd();
        this.phNum = usrUpdateDto.getNextPwd();
        this.cash = usrUpdateDto.getCash();
        this.useYn = usrUpdateDto.getUseYn();
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
