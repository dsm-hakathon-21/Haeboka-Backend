package com.hackathon.dsmhackathon21.domain.quiz.controller;

import com.hackathon.dsmhackathon21.domain.quiz.dto.QuizResponse;
import com.hackathon.dsmhackathon21.domain.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{wordbookId}")
    public Mono<QuizResponse> getQuiz(@PathVariable String wordbookId) {
        return quizService.generateQuiz(wordbookId);
    }
}