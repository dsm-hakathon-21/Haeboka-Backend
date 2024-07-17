package com.hackathon.dsmhackathon21.domain.edu.controller;

import com.hackathon.dsmhackathon21.domain.edu.service.UpdateGrammarBookService;
import com.hackathon.dsmhackathon21.domain.edu.service.UpdateWordBookService;
import com.hackathon.dsmhackathon21.global.util.ListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eng")
public class EngController {
    private final UpdateGrammarBookService updateGrammarBookService;
    private final UpdateWordBookService updateWordBookService;

    @PatchMapping("/grammar")
    public Mono<Void> updateGrammarBook(@RequestBody ListRequest<String> listRequest) {
        return updateGrammarBookService.update(listRequest.list());
    }

    @PatchMapping("/word")
    public Mono<Void> updateWordBook(@RequestBody ListRequest<String> listRequest) {
        return updateWordBookService.update(listRequest.list());
    }
}
