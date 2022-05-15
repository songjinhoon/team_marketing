package com.devteam.marketing.domain.usr.root.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Social {

    GOOGLE("SOCIAL_GOOGLE", "구글"),
    NAVER("SOCIAL_NAVER", "네이버"),
    KAKAO("SOCIAL_KAKAO", "카카오"),
    FACEBOOK("SOCIAL_FACEBOOK", "페이스북");

    private String key;

    private String value;

}
