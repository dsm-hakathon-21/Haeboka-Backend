package com.hackathon.dsmhackathon21.global.error;

import lombok.Getter;

@Getter
public enum Error {
    UNAUTHENTICATED_EMAIL(403, "이메일 인증이 되지 않았습니다."),

    TEST(400, "TEST");

    private final int status;
    private final String message;
    private final String detail = this.name().toLowerCase();

    Error(int status, String message) {
        this.status = status;
        this.message = message;
    }
}