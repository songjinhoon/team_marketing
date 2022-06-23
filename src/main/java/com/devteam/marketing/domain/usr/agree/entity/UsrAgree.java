package com.devteam.marketing.domain.usr.agree.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeInsertDto;
import com.devteam.marketing.domain.usr.entity.Usr;
import lombok.*;

import javax.persistence.*;

@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static UsrAgree create(UsrAgreeInsertDto usrAgreeInsertDto) {
        return UsrAgree.builder()
                .usr(usrAgreeInsertDto.getUsr())
                .agree(usrAgreeInsertDto.getAgree())
                .agreeYn(usrAgreeInsertDto.getAgreeYn())
                .build();
    }

}
