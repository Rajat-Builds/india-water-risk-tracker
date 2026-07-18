package com.rajat.waterrisktracker.service;

import com.rajat.waterrisktracker.entity.GroundWaterStressLevel;
import com.rajat.waterrisktracker.repository.GroundWaterStressLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundwaterStressLevelService {

    @Autowired
    private GroundWaterStressLevelRepository groundwaterStressLevelRepository;

    public List<GroundWaterStressLevel> getAllLevels() {
        return groundwaterStressLevelRepository.findAll();
    }

    public GroundWaterStressLevel getLevelById(Long id) {
        return groundwaterStressLevelRepository.findById(id).orElse(null);
    }

    public GroundWaterStressLevel createLevel(GroundWaterStressLevel level) {
        return groundwaterStressLevelRepository.save(level);
    }

    public GroundWaterStressLevel updateLevel(Long id, GroundWaterStressLevel updatedLevel) {
        GroundWaterStressLevel existing = groundwaterStressLevelRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setDistrict(updatedLevel.getDistrict());
        existing.setState(updatedLevel.getState());
        existing.setStressCategory(updatedLevel.getStressCategory());
        existing.setAssessmentYear(updatedLevel.getAssessmentYear());
        existing.setSource(updatedLevel.getSource());
        return groundwaterStressLevelRepository.save(existing);
    }

    public boolean deleteLevel(Long id) {
        if (!groundwaterStressLevelRepository.existsById(id)) {
            return false;
        }
        groundwaterStressLevelRepository.deleteById(id);
        return true;
    }
}