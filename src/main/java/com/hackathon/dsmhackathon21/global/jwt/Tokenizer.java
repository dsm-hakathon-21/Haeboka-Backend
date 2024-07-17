package com.hackathon.dsmhackathon21.global.jwt;

import com.hackathon.dsmhackathon21.domain.user.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.authentication.AnonymousAuthenticationWebFilter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Component
public class Tokenizer {
    @Value("${jwt.secret}")
    private String secret;

    private static final String TOKEN_PREFIX = "Bearer ";

    public Mono<String> tokenize(String input) {
        var now = new Date();
        return Mono.just(
                Jwts.builder()
                        .setHeaderParam("type", "jwt")
                        .setSubject(input)
                        .setIssuedAt(now)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .setExpiration(new Date(now.getTime() + 1000 * 60 * 60 * 24))
                        .compact()
        );
    }

    public String removePrefix(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public Mono<Authentication> getAuthentication(String token) {
        return parseClaims(token)
                .flatMap(claims -> createAuthenticatedUserBySubject(claims.getSubject()))
                .map(details -> new UsernamePasswordAuthenticationToken(
                        details, null, details.getAuthorities()));
    }

    private Mono<UserDetails> createAuthenticatedUserBySubject(String subject) {
        return Mono.justOrEmpty(new User(subject, "", List.of()));
    }


    private Mono<Claims> parseClaims(String token) {
        return Mono.fromCallable(() -> Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody());
    }
}
