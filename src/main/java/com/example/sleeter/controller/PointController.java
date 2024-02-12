package com.example.sleeter.controller;


import com.example.sleeter.dto.DirtyPointDto;
import com.example.sleeter.dto.PointDto;
import com.example.sleeter.persistence.service.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/point")
public class PointController {
    private final PointService pointService;

    @GetMapping(value = "/all")
    public List<PointDto> getAllPoints() {
        return pointService.getUserPoints();
    }

    @PostMapping(value = "/add")
    public List<PointDto> addPoint(@RequestBody @Valid DirtyPointDto dto) {
        return pointService.addPoint(dto);
    }

    @DeleteMapping(value = "/delete")
    public List<PointDto> deletePoints() {
        return pointService.deleteUserPoints();
    }
}

