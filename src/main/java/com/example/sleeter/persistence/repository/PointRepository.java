package com.example.sleeter.persistence.repository;

import com.example.sleeter.persistence.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findPointsByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
