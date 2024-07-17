package com.hackathon.dsmhackathon21.domain.edu.repository;

import com.hackathon.dsmhackathon21.domain.edu.model.WordBookEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WordBookMongoRepository extends ReactiveMongoRepository<WordBookEntity, String> {
    Mono<Boolean> existsByKeyword(String keyword);
}
