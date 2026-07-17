package com.rajat.waterrisktracker.repository;

import com.rajat.waterrisktracker.entity.WaterUsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterUsageRecordRepository extends JpaRepository<WaterUsageRecord, Long> {
}

