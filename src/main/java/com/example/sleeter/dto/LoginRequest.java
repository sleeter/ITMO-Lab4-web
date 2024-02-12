package com.example.sleeter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotEmpty
        String login,
        @NotBlank
        @Size(min = 7)
        String password
) {
}
