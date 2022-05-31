package com.devteam.marketing.domain.usr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Social {

    NONE("NONE", "미사용"),
    GOOGLE("SOCIAL_GOOGLE", "구글"),
    NAVER("SOCIAL_NAVER", "네이버"),
    KAKAO("SOCIAL_KAKAO", "카카오"),
    FACEBOOK("SOCIAL_FACEBOOK", "페이스북");

    private String key;

    private String value;

}
