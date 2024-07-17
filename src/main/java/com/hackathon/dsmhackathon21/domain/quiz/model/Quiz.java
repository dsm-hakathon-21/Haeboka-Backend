package com.hackathon.dsmhackathon21.domain.quiz.model;

import java.util.List;

public record Quiz(
        String currentWord,
        List<String> options,
        String correctAnswer
) {
    public static Quiz of(String currentWord, List<String> options, String correctAnswer) {
        return new Quiz(currentWord, options, correctAnswer);
    }
}
