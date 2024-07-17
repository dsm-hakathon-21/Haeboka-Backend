package com.hackathon.dsmhackathon21.global.security.config;

import com.hackathon.dsmhackathon21.global.jwt.JwtFilter;
import com.hackathon.dsmhackathon21.global.jwt.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final Tokenizer tokenizer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/user/register", "/api/user/login").permitAll()
                        .anyExchange().permitAll())
                .addFilterBefore(new JwtFilter(tokenizer), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }
}
