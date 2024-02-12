package com.example.sleeter.utils;


import com.example.sleeter.dto.PointDto;
import com.example.sleeter.persistence.entity.Point;


public class PointMapper {
    public static PointDto mapper(Point point) {
        return new PointDto(point.getX(), point.getY(), point.getR(), point.getTime(), point.getScriptTime(), point.isHit());
    }
}
