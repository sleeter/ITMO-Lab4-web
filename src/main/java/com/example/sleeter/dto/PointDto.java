package com.example.sleeter.dto;

public record PointDto(
        Double x,
        Double y,
        Double r,
        String time,
        Long scriptTime,
        Boolean isHit
) {
}
