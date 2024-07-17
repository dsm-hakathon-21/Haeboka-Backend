package com.hackathon.dsmhackathon21.domain.quiz.dto;

import com.hackathon.dsmhackathon21.domain.quiz.model.Quiz;

import java.util.List;

public record QuizResponse(
        List<Quiz> quizList
) {
}
