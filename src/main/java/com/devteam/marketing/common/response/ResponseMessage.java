package com.devteam.marketing.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    SUCCESS_CREATE("등록되었습니다."),
    SUCCESS_READ("조회되었습니다."),
    SUCCESS_UPDATE("수정되었습니다."),
    SUCCESS_DELETE("삭제되었습니다."),

    ERROR_INTERNAL_SERVER_ERROR("서버에 문제가 있습니다. 잠시후 다시 시도해주세요."),
    ERROR_NOT_FOUND("값을 찾을 수 없습니다.");

    private final String value;

}
