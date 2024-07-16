package com.hackathon.dsmhackathon21.domain.user.dto.request;

public record UserRegisterRequest(
        String nickname,
        String email,
        String password
) {
}
