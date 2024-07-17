package com.hackathon.dsmhackathon21.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
        LocalDateTime timestamp,
        String path,
        String exception,
        String detail
) {
    public static ResponseEntity<ErrorResponse> ofCustomException(CustomException e, ServerHttpRequest request) {
        var error = e.getError();
        return ResponseEntity.status(error.getStatus())
                .body(new ErrorResponse(
                        error.getStatus(),
                        error.getMessage(),
                        LocalDateTime.now(),
                        request.getPath().toString(),
                        e.getClass().getSimpleName(),
                        error.getDetail()
                ));
    }

    public static ErrorResponse ofSecurityError(HttpStatus status, String message, ServerWebExchange exchange, Exception e) {
        return new ErrorResponse(
                status.value(),
                message,
                LocalDateTime.now(),
                exchange.getRequest().getPath().toString(),
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }
}
