package com.hackathon.dsmhackathon21.domain.edu.controller;

import com.hackathon.dsmhackathon21.domain.edu.dto.AddWordBookRequest;
import com.hackathon.dsmhackathon21.domain.edu.dto.KeyWordsResponse;
import com.hackathon.dsmhackathon21.domain.edu.dto.WordsResponse;
import com.hackathon.dsmhackathon21.domain.edu.service.AddWordBookService;
import com.hackathon.dsmhackathon21.domain.edu.service.FindWordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wordbook")
public class WordBookController {
    private final AddWordBookService addWordBookService;
    private final FindWordBookService findWordBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addWordBook(@RequestBody AddWordBookRequest request) {
        return addWordBookService.add(request);
    }

    @GetMapping("/{id}")
    public Mono<WordsResponse> getWords(@PathVariable String id) {
        return findWordBookService.getWords(id);
    }

    @GetMapping
    public Mono<KeyWordsResponse> getKeyWords() {
        return findWordBookService.getKeywords();
    }
}
