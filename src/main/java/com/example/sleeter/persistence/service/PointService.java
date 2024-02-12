package com.example.sleeter.persistence.service;


import com.example.sleeter.dto.DirtyPointDto;
import com.example.sleeter.dto.PointDto;

import java.util.List;

public interface PointService {
    List<PointDto> getUserPoints();

    List<PointDto> addPoint(DirtyPointDto dto);

    Long getUserIdFromToken();

    List<PointDto> deleteUserPoints();
}
