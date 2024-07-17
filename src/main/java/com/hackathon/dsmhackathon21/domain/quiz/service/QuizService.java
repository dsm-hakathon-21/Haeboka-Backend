package com.hackathon.dsmhackathon21.domain.quiz.service;

import com.hackathon.dsmhackathon21.domain.edu.model.Word;
import com.hackathon.dsmhackathon21.domain.edu.service.FindWordBookService;
import com.hackathon.dsmhackathon21.domain.quiz.dto.QuizResponse;
import com.hackathon.dsmhackathon21.domain.quiz.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private static final int NUMBER_OF_OPTIONS = 5;
    private static final int NUMBER_OF_INCORRECT_OPTIONS = NUMBER_OF_OPTIONS - 1;

    private final FindWordBookService findWordBookService;

    public Mono<QuizResponse> generateQuiz(String wordbookId) {
        return findWordBookService.getWords(wordbookId)
                .map(result -> createQuizResponse(result.words()));
    }

    private QuizResponse createQuizResponse(List<Word> words) {
        List<Word> shuffledWords = new ArrayList<>(words);
        Collections.shuffle(shuffledWords);

        List<Quiz> quizList = shuffledWords.stream()
                .map(word -> createQuiz(word, words))
                .toList();

        return new QuizResponse(quizList);
    }

    private Quiz createQuiz(Word correctWord, List<Word> allWords) {
        return Quiz.of(correctWord.eng(), getOptions(allWords, correctWord), correctWord.kor());
    }

    private List<String> getOptions(List<Word> allWords, Word correctWord) {
        List<String> options = new ArrayList<>();
        options.add(correctWord.kor());

        List<Word> incorrectWords = allWords.stream()
                .filter(word -> !word.equals(correctWord))
                .collect(Collectors.toList());

        Collections.shuffle(incorrectWords);

        List<String> incorrectOptions = incorrectWords.stream()
                .limit(NUMBER_OF_INCORRECT_OPTIONS)
                .map(Word::kor)
                .toList();

        options.addAll(incorrectOptions);
        Collections.shuffle(options);

        return options;
    }
}