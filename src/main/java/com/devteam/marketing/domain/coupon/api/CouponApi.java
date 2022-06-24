package com.devteam.marketing.domain.coupon.api;

import com.devteam.marketing.common.response.ResponseDto;
import com.devteam.marketing.domain.coupon.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/coupon")
@RestController
public class CouponApi {

    private final CouponService couponService;

    @ApiOperation(value = "전체 동의 조회", notes = "전체 동의 목록을 조회한다")
    @GetMapping(value = "/findAllToSimple")
    public ResponseEntity<ResponseDto> findAllToSimple() {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(couponService.findAllToSimple())
                .build());
    }

}
