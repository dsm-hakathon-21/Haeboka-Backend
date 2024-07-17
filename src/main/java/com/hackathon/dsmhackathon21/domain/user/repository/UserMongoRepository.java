package com.hackathon.dsmhackathon21.domain.user.repository;

import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Component
public interface UserMongoRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<Boolean> existsByEmail(String email);
    Mono<UserEntity> findByNickname(String nickname);
}
