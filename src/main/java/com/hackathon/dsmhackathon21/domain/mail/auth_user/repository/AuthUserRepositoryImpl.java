package com.hackathon.dsmhackathon21.domain.mail.auth_user.repository;

import com.hackathon.dsmhackathon21.global.redis.RedisKey;
import com.hackathon.dsmhackathon21.global.redis.operation.RedisOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class AuthUserRepositoryImpl implements AuthUserRepository {
    private static final String AUTH_MAIL = RedisKey.AUTH_USER.getKey();
    private final RedisOperation redisOperation;

    @Override
    public Mono<Boolean> save(String mail) {
        return handleError(redisOperation.save(
                AUTH_MAIL + mail,
                mail,
                Duration.ofHours(1)
        ));
    }

    @Override
    public Mono<Boolean> existsByEmail(String mail) {
        return handleError(redisOperation.hasKey(AUTH_MAIL + mail));
    }

    @Override
    public Mono<Void> delete(String mail) {
        return handleError(redisOperation.delete(AUTH_MAIL + mail));
    }

    private static <T> Mono<T> handleError(Mono<T> mono) {
        return mono.onErrorMap(e -> e instanceof RedisConnectionFailureException ? e : e); // 추후 커스텀 예외 처리 예정
    }
}
