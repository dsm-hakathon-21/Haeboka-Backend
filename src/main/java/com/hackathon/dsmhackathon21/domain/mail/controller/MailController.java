package com.hackathon.dsmhackathon21.domain.mail.controller;

import com.hackathon.dsmhackathon21.domain.mail.service.UserMailSendService;
import com.hackathon.dsmhackathon21.domain.mail.service.UserMailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {
    private final UserMailSendService userMailSendService;
    private final UserMailVerifyService userMailVerifyService;

    @PostMapping("/send")
    public Mono<Void> send(@RequestParam String to) {
        return userMailSendService.send(to);
    }

    @PostMapping("/verify")
    public Mono<Void> verify(
            @RequestParam String to,
            @RequestParam String code
    ) {
        return userMailVerifyService.verify(to, code);
    }
}
