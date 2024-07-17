package com.hackathon.dsmhackathon21.domain.user.dto.request;

public record UserLoginRequest(
        String nickname,
        String password
) {
}
