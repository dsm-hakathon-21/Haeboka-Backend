package com.hackathon.dsmhackathon21.domain.edu.model;

import com.hackathon.dsmhackathon21.domain.edu.dto.AddWordBookRequest;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "wordbooks")
public class WordBookEntity {
    @Id
    private String id;
    private final String keyword;
    private final List<Word> words;

    private WordBookEntity(String keyword, List<Word> words) {
        this.keyword = keyword;
        this.words = words;
    }

    public static WordBookEntity createEntity(String keyword, List<Word> words) {
        return new WordBookEntity(keyword, words);
    }

    public static WordBookEntity createEntityByAddWordBookRequest(AddWordBookRequest request) {
        return createEntity(request.keyword(), request.words());
    }
}
