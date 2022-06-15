package com.devteam.marketing.domain.coupon.dto;

import com.devteam.marketing.domain.BaseDto;
import com.devteam.marketing.domain.coupon.entity.Coupon;
import com.devteam.marketing.domain.coupon.mapper.CouponMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponCreateRequestDto extends BaseDto {

    @Size(min = 1, max = 100)
    @NotBlank
    private String name;

    @Max(value = 10_000)
    @Min(value = 10)
    private Integer discount;

    @Max(100_000)
    @PositiveOrZero
    private Integer minOrderAmount;

    @NotBlank
    private String discountType;

    @NotBlank
    private String issuerType;

    @NotNull
    private Integer issuerId;

    @Max(10_000)
    @Min(1)
    private Integer maxCnt;

    private Integer allocatedCount;

    @Max(5)
    @Min(1)
    private Integer maxCntPerUsr;

    private String promotionCode;

    @NotBlank
    private String expirationType;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @Max(365)
    @Min(0)
    private Integer daysFromIssuance;

    public CouponCreateRequestDto(
            String name, Integer discount, Integer minOrderAmount,
            String discountType, String issuerType, Integer issuerId, Integer maxCnt,
            Integer allocatedCount, Integer maxCntPerUsr, String promotionCode,
            String expirationType, LocalDateTime startDate, LocalDateTime finishDate
    ) {
        this.name = name;
        this.discount = discount;
        this.minOrderAmount = minOrderAmount;
        this.discountType = discountType;
        this.issuerType = issuerType;
        this.issuerId = issuerId;
        this.maxCnt = maxCnt;
        this.maxCntPerUsr = maxCntPerUsr;
        this.promotionCode = promotionCode;
        this.expirationType = expirationType;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
