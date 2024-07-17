package com.hackathon.dsmhackathon21.domain.edu.dto;

public record WordBookResponse(
        String id,
        String keyword
) {
    public static WordBookResponse of(String id, String keyword) {
        return new WordBookResponse(id, keyword);
    }
}

