package com.hackathon.dsmhackathon21.domain.edu.dto;

import com.hackathon.dsmhackathon21.domain.edu.model.Word;
import com.hackathon.dsmhackathon21.domain.edu.model.WordBookEntity;

import java.util.List;

public record WordsResponse(
        List<Word> words
) {
    public static WordsResponse fromWordBookEntity(WordBookEntity entity) {
        return new WordsResponse(entity.getWords());
    }
}
