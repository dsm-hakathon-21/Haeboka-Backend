package com.hackathon.dsmhackathon21.global.error.exception;

import com.hackathon.dsmhackathon21.global.error.CustomException;
import com.hackathon.dsmhackathon21.global.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleCustomException(CustomException e, ServerHttpRequest request) {
        return Mono.just(
                ErrorResponse.ofCustomException(e, request)
        );
    }
}
