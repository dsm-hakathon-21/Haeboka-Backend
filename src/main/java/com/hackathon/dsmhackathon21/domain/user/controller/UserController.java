package com.hackathon.dsmhackathon21.domain.user.controller;

import com.hackathon.dsmhackathon21.domain.user.dto.request.UserLoginRequest;
import com.hackathon.dsmhackathon21.domain.user.dto.request.UserRegisterRequest;
import com.hackathon.dsmhackathon21.domain.user.dto.response.UserLoginResponse;
import com.hackathon.dsmhackathon21.domain.user.service.UserLoginService;
import com.hackathon.dsmhackathon21.domain.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserLoginService userLoginService;
    private final UserRegisterService userRegisterService;

    @PostMapping("/register")
    public Mono<Void> register(@RequestBody UserRegisterRequest request) {
        return userRegisterService.register(request);
    }

    @PostMapping("/login")
    public Mono<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return userLoginService.login(request);
    }
}
