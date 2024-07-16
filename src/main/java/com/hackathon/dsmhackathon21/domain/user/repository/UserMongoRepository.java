package com.hackathon.dsmhackathon21.domain.user.repository;

import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserMongoRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<Boolean> existsByEmail(String email);
}
