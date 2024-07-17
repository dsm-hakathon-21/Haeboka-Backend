package com.hackathon.dsmhackathon21.domain.edu.service;

import com.hackathon.dsmhackathon21.domain.edu.dto.KeyWordsResponse;
import com.hackathon.dsmhackathon21.domain.edu.dto.WordBookResponse;
import com.hackathon.dsmhackathon21.domain.edu.dto.WordsResponse;
import com.hackathon.dsmhackathon21.domain.edu.exception.WordbookNotFoundException;
import com.hackathon.dsmhackathon21.domain.edu.repository.WordBookMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FindWordBookService {
    private final WordBookMongoRepository wordBookMongoRepository;

    public Mono<WordsResponse> getWords(String wordBookId) {
        return wordBookMongoRepository.findById(wordBookId)
                .switchIfEmpty(Mono.error(WordbookNotFoundException.EXCEPTION))
                .map(WordsResponse::fromWordBookEntity);
    }

    public Mono<KeyWordsResponse> getKeywords() {
        return wordBookMongoRepository.findAll()
                .map(wordbook -> WordBookResponse.of(wordbook.getId(), wordbook.getKeyword()))
                .collectList()
                .map(KeyWordsResponse::new);
    }
}
