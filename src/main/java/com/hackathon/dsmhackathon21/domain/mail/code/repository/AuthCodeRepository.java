package com.hackathon.dsmhackathon21.domain.mail.code.repository;

import com.hackathon.dsmhackathon21.domain.mail.code.model.AuthCodeModel;
import reactor.core.publisher.Mono;

public interface AuthCodeRepository {
    Mono<Boolean> save(AuthCodeModel model);
    Mono<AuthCodeModel> findByMail(String email);
    Mono<Void> delete(AuthCodeModel model);

}