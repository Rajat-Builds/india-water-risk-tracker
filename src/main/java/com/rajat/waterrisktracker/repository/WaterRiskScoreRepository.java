package com.rajat.waterrisktracker.repository;

import com.rajat.waterrisktracker.entity.WaterRiskScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRiskScoreRepository extends JpaRepository<WaterRiskScore, Long> {
}
