package com.hackathon.dsmhackathon21.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.authentication.AnonymousAuthenticationWebFilter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class Tokenizer {
    @Value("${jwt.secret}")
    private String secret;

    public Mono<String> tokenize(String input) {
        var now = new Date();
        return Mono.just(
                Jwts.builder()
                        .setHeaderParam("type", "jwt")
                        .claim("input", input)
                        .setIssuedAt(now)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .setExpiration(new Date(now.getTime() + 1000 * 60 * 60 * 24))
                        .compact()
        );
    }
}
