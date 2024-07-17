package com.hackathon.dsmhackathon21.domain.translate.service;

import com.hackathon.dsmhackathon21.domain.translate.dto.TranslationRequest;
import com.hackathon.dsmhackathon21.domain.translate.dto.TranslationResponse;
import com.hackathon.dsmhackathon21.domain.translate.exception.UnavailableTranslationServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private final WebClient webClient;

    @Value("${translate.api.url}")
    private String translationApiUrl;

    public Mono<TranslationResponse> translate(TranslationRequest request) {
        return webClient.post()
                .uri(translationApiUrl + "/translate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .onErrorResume(WebClientRequestException.class, e ->
                        Mono.error(UnavailableTranslationServerException.EXCEPTION)
                );
    }
}