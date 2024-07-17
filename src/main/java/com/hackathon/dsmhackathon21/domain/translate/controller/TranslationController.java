package com.hackathon.dsmhackathon21.domain.translate.controller;

import com.hackathon.dsmhackathon21.domain.translate.dto.TranslationRequest;
import com.hackathon.dsmhackathon21.domain.translate.dto.TranslationResponse;
import com.hackathon.dsmhackathon21.domain.translate.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping
    public Mono<TranslationResponse> translate(@RequestBody TranslationRequest request) {
        return translationService.translate(request);
    }
}