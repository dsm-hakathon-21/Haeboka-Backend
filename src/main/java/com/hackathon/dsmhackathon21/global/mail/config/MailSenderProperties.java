package com.hackathon.dsmhackathon21.global.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail.sender")
public record MailSenderProperties(
        String host,
        String email,
        String password,
        Boolean auth,
        Boolean starttlsRequired,
        Boolean starttlsEnable,
        String socketFactoryClass,
        Boolean socketFactoryFallback,
        Integer port
) {
}
