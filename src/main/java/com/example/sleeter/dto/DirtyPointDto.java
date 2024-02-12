package com.example.sleeter.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DirtyPointDto (
        @NotNull @Min(-5) @Max(3)
        Double x,
        @NotNull @Min(-5) @Max(3)
        Double y,
        @NotNull @Min(-5) @Max(3)
        Double r
) {
}
