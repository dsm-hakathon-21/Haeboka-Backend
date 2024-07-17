package com.hackathon.dsmhackathon21.domain.user.service;

import com.hackathon.dsmhackathon21.domain.user.dto.request.UserLoginRequest;
import com.hackathon.dsmhackathon21.domain.user.dto.response.UserLoginResponse;
import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import com.hackathon.dsmhackathon21.domain.user.repository.UserMongoRepository;
import com.hackathon.dsmhackathon21.global.jwt.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserMongoRepository userMongoRepository;
    private final PasswordEncoder passwordEncoder;
    private final Tokenizer tokenizer;

    public Mono<UserLoginResponse> login(UserLoginRequest request) {
        return userMongoRepository.findByNickname(request.nickname())
                .switchIfEmpty(Mono.error(new RuntimeException())) // 유저 찾지 못할 때
                .filter((UserEntity user) -> passwordEncoder.matches(request.password(), user.getPassword()))
                .switchIfEmpty(Mono.error(new RuntimeException())) // 비밀번호 불 일치
                .flatMap(user -> tokenizer.tokenize(user.getNickname()))
                .map(UserLoginResponse::new);
    }
}
