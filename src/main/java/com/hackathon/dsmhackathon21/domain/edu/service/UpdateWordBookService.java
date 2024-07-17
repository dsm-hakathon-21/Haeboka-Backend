package com.hackathon.dsmhackathon21.domain.edu.service;

import com.hackathon.dsmhackathon21.domain.user.component.CurrentUser;
import com.hackathon.dsmhackathon21.domain.user.repository.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateWordBookService {
    private final UserMongoRepository userMongoRepository;
    private final CurrentUser currentUser;

    public Mono<Void> update(List<String> newWordBook) {
        return currentUser.get()
                //유저에 newWordBook을 저장함 -> 업데이트
                .doOnNext(user -> user.updateWordBook(newWordBook))
                .flatMap(userMongoRepository::save)
                .then();
    }
}
