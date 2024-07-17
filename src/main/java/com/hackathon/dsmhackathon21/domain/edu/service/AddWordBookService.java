package com.hackathon.dsmhackathon21.domain.edu.service;

import com.hackathon.dsmhackathon21.domain.edu.dto.AddWordBookRequest;
import com.hackathon.dsmhackathon21.domain.edu.exception.AlreadyExistsKeywordException;
import com.hackathon.dsmhackathon21.domain.edu.model.WordBookEntity;
import com.hackathon.dsmhackathon21.domain.edu.repository.WordBookMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddWordBookService {
    private final WordBookMongoRepository wordBookMongoRepository;

    public Mono<Void> add(AddWordBookRequest request) {
        return validateInputKeyword(request.keyword())
                .then(Mono.defer(() -> addWordBookProcess(request)));
    }

    private Mono<Void> addWordBookProcess(AddWordBookRequest request) {
        var entity = WordBookEntity.createEntityByAddWordBookRequest(request);
        return wordBookMongoRepository.save(entity)
                .then();
    }

    private Mono<Void> validateInputKeyword(String keyword) {
        return wordBookMongoRepository.existsByKeyword(keyword)
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(AlreadyExistsKeywordException.EXCEPTION))
                .then();
    }
}
