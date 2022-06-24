package com.devteam.marketing.domain.coupon.entity;

import com.devteam.marketing.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;
    // 쿠폰 이름
    private String name;
    // 할인 유형 <== enum 으로해야하는지?
    private String discountType;
    // 할인 금액
    private Integer discount;
    // 시작일자
    private LocalDateTime startDate;
    // 만료일자
    private LocalDateTime finishDate;
    // 최소 주문금액
    private Integer minOrderAmount;
    // 발급 주체
    private String issuerType;
    // 발급 주체 id
    private Integer issuerId;
    // 최대 발행 수
    private Integer maxCnt;
    // 고객당 최대 발행 수
    private Integer maxCntPerUsr;
    // 쿠폰코드 (입력형을 위해)
    private String promotionCode;
    // 유효기간 정책 <== enum 으로해야하는지?
    private String expirationType;

    /*
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<UsrAgree> usrCoupons = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<UsrAgree> storeCoupons = new ArrayList<>();
    */
}
