package com.hackathon.dsmhackathon21.domain.user.component;

import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import com.hackathon.dsmhackathon21.domain.user.repository.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CurrentUser {
    private final UserMongoRepository userMongoRepository;

    public Mono<UserEntity> get() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(userMongoRepository::findByNickname);
    }
}
