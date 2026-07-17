package com.rajat.waterrisktracker.repository;

import com.rajat.waterrisktracker.entity.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCenterRepository extends JpaRepository<DataCenter, Long> {
}
