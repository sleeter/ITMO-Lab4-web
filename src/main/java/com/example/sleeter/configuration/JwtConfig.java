package com.example.sleeter.configuration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
        @NotBlank String secret,
        long exp
) {
}