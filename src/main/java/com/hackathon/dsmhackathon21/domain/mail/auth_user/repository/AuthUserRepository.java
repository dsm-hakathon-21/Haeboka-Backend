package com.hackathon.dsmhackathon21.domain.mail.auth_user.repository;

import reactor.core.publisher.Mono;

public interface AuthUserRepository {
    Mono<Boolean> save(String mail);
    Mono<Boolean> existsByEmail(String mail);
    Mono<Void> delete(String mail);

}