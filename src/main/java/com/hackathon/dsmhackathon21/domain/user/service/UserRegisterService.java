package com.hackathon.dsmhackathon21.domain.user.service;

import com.hackathon.dsmhackathon21.domain.mail.auth_user.repository.AuthUserRepository;
import com.hackathon.dsmhackathon21.domain.user.dto.request.UserRegisterRequest;
import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import com.hackathon.dsmhackathon21.domain.user.repository.UserMongoRepository;
import com.hackathon.dsmhackathon21.global.error.exception.UnauthenticatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserMongoRepository userMongoRepository;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<Void> register(UserRegisterRequest request) {
        return Mono.when(
                        authenticationVerify(request.email()),
                        validateAlreadyExistEmail(request.email()))
                .then(Mono.defer((() ->
                        userMongoRepository.save(
                                UserEntity.createEntity(
                                        request.nickname(),
                                        request.email(),
                                        passwordEncoder.encode(request.password())
                                ))
                )))
                .then();
    }

    private Mono<Void> authenticationVerify(String email) {
        return authUserRepository.existsByEmail(email)
                .filter((Boolean bool) -> bool)
                .switchIfEmpty(Mono.error(UnauthenticatedEmailException.EXCEPTION))
                .then();
    }

    private Mono<Void> validateAlreadyExistEmail(String email) {
        return userMongoRepository.existsByEmail(email)
                .filter((Boolean bool) -> !bool)
                .switchIfEmpty(Mono.error(new RuntimeException())) // 커스텀 예외 처리 예정
                .then();
    }
}
