package com.hackathon.dsmhackathon21.domain.translate.dto;

public record TranslationRequest(
        String q,
        String source,
        String target
){
}