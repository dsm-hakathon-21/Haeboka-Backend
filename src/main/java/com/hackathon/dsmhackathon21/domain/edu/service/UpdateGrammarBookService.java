package com.hackathon.dsmhackathon21.domain.edu.service;

import com.hackathon.dsmhackathon21.domain.user.component.CurrentUser;
import com.hackathon.dsmhackathon21.domain.user.repository.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateGrammarBookService {
    private final UserMongoRepository userMongoRepository;
    private final CurrentUser currentUser;

    public Mono<Void> update(List<String> newGrammarBook) {
        return currentUser.get()
                .doOnNext(user -> user.updateGrammarBook(newGrammarBook))
                .flatMap(userMongoRepository::save)
                .then();
    }
}
