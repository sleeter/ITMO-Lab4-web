package com.example.sleeter.persistence.service.impl;

import com.example.sleeter.dto.DirtyPointDto;
import com.example.sleeter.dto.PointDto;
import com.example.sleeter.persistence.entity.Point;
import com.example.sleeter.persistence.repository.PointRepository;
import com.example.sleeter.persistence.service.PointService;
import com.example.sleeter.utils.PointMapper;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    @Override
    public List<PointDto> getUserPoints() {
        return pointRepository.findPointsByUserId(getUserIdFromToken())
                .stream()
                .map(PointMapper::mapper)
                .toList();
    }

    @Override
    public List<PointDto> addPoint(DirtyPointDto dto) {
        long timer = System.nanoTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        ZonedDateTime moscowDateTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        Point point = new Point(dto, getUserIdFromToken());
        String currentTime = formatter.format(moscowDateTime);
        point.setTime(currentTime);
        point.setScriptTime((long) ((System.nanoTime() - timer) * 0.001) );

        pointRepository.save(point);
        return getUserPoints();
    }

    @Override
    public Long getUserIdFromToken() {
        Claims credentials = (Claims) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return Long.parseLong((String) credentials.get("userId"));
    }

    @Override
    public List<PointDto> deleteUserPoints() {
        pointRepository.deleteAllByUserId(getUserIdFromToken());
        return new ArrayList<>();
    }
}
