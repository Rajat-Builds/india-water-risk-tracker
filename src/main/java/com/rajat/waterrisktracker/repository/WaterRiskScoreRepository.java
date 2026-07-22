package com.rajat.waterrisktracker.repository;

import com.rajat.waterrisktracker.entity.WaterRiskScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterRiskScoreRepository extends JpaRepository<WaterRiskScore, Long> {
    List<WaterRiskScore> findByDataCenterIdOrderByCalculatedAtDesc(Long dataCenterId);
}