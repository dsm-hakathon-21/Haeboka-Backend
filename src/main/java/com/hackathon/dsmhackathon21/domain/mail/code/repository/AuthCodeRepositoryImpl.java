package com.hackathon.dsmhackathon21.domain.mail.code.repository;

import com.hackathon.dsmhackathon21.domain.mail.code.model.AuthCodeModel;
import com.hackathon.dsmhackathon21.global.redis.RedisKey;
import com.hackathon.dsmhackathon21.global.redis.operation.RedisOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
@Slf4j(topic = "인증 코드 레포지토리")
public class AuthCodeRepositoryImpl implements AuthCodeRepository {
    private static final String AUTH_CODE = RedisKey.AUTH_CODE.getKey();
    private final RedisOperation redisOperation;

    @Override
    public Mono<Boolean> save(AuthCodeModel model) {
        return handleError(redisOperation.save(
                AUTH_CODE + model.email(),
                model.code(),
                Duration.ofMinutes(30)
        ));
    }

    @Override
    public Mono<AuthCodeModel> findByMail(String email) {
        return handleError(redisOperation.getValue(AUTH_CODE + email)
                .map(code -> AuthCodeModel.of(email, code)));
    }

    @Override
    public Mono<Void> delete(AuthCodeModel model) {
        return handleError(redisOperation.delete(AUTH_CODE + model.email()));
    }

    private static <T> Mono<T> handleError(Mono<T> mono) {
        return mono.onErrorMap(e -> e instanceof RedisConnectionFailureException ? e : e); // 추후 커스텀 예외 적용 예정
    }
}