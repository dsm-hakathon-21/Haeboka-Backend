package com.hackathon.dsmhackathon21.global.error;

import lombok.Getter;

@Getter
public enum Error {
    UNAUTHENTICATED_EMAIL(403, "이메일 인증이 되지 않았습니다."),

    WORDBOOK_NOT_FOUND(404, "단어장을 찾지 못 했습니다."),

    ALREADY_EXISTS_KEYWORD(409, "이미 단어장이 존재하는 키워드입니다."),

    UNAVAILABLE_TRANSLATION_SERVER(503, "번역 서버를 사용할 수 없습니다."),

    TEST(400, "TEST");

    private final int status;
    private final String message;
    private final String detail = this.name().toLowerCase();

    Error(int status, String message) {
        this.status = status;
        this.message = message;
    }
}