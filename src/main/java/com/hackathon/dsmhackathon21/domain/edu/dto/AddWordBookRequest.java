package com.hackathon.dsmhackathon21.domain.edu.dto;

import com.hackathon.dsmhackathon21.domain.edu.model.Word;

import java.util.List;

public record AddWordBookRequest(
        String keyword,
        List<Word> words
) {
}
