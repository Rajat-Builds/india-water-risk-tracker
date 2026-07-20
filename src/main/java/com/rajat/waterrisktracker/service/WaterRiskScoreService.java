package com.rajat.waterrisktracker.service;

import com.rajat.waterrisktracker.entity.*;
import com.rajat.waterrisktracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WaterRiskScoreService {

    @Autowired
    private WaterRiskScoreRepository waterRiskScoreRepository;

    @Autowired
    private DataCenterRepository dataCenterRepository;

    @Autowired
    private GroundWaterStressLevelRepository groundWaterStressLevelRepository;

    @Autowired
    private WaterUsageRecordRepository waterUsageRecordRepository;

    public List<WaterRiskScore> getAllScores() {
        return waterRiskScoreRepository.findAll();
    }

    public WaterRiskScore getScoreById(Long id) {
        return waterRiskScoreRepository.findById(id).orElse(null);
    }

    public WaterRiskScore getLatestScoreForDataCenter(Long dataCenterId) {
    return waterRiskScoreRepository.findAll().stream()
        .filter(s -> s.getDataCenter().getId().equals(dataCenterId))
        .max((a, b) -> a.getCalculatedAt().compareTo(b.getCalculatedAt()))
        .orElse(null);
}

    public WaterRiskScore calculateRiskScore(Long dataCenterId) {
        DataCenter dataCenter = dataCenterRepository.findById(dataCenterId).orElse(null);
        if (dataCenter == null) {
            return null;
        }


        // 1. Groundwater stress score
        List<GroundWaterStressLevel> stressLevels = groundWaterStressLevelRepository.findAll();
        int groundwaterScore = 0;
        for (GroundWaterStressLevel level : stressLevels) {
            if (level.getDistrict().equalsIgnoreCase(dataCenter.getDistrict())) {
                groundwaterScore = mapStressCategoryToScore(level.getStressCategory());
                break;
            }
        }

        // 2. Facility scale score
        int scaleScore = mapScaleToScore(dataCenter.getFacilityScaleMW());

        // 3. Disclosure transparency score
        List<WaterUsageRecord> records = waterUsageRecordRepository.findAll();
        String disclosureLevel = "NONE";
        for (WaterUsageRecord record : records) {
            if (record.getDataCenter().getId().equals(dataCenterId)) {
                disclosureLevel = record.getSourceType();
                break;
            }
        }
        int disclosureScore = mapDisclosureToScore(disclosureLevel);

        int totalScore = groundwaterScore + scaleScore + disclosureScore;
        String riskCategory = mapTotalToCategory(totalScore);

        WaterRiskScore riskScore = new WaterRiskScore();
        riskScore.setDataCenter(dataCenter);
        riskScore.setGroundwaterStressScore(groundwaterScore);
        riskScore.setFacilityScaleScore(scaleScore);
        riskScore.setDisclosureTransparencyScore(disclosureScore);
        riskScore.setTotalScore(totalScore);
        riskScore.setRiskCategory(riskCategory);
        riskScore.setCalculatedAt(LocalDateTime.now());

        return waterRiskScoreRepository.save(riskScore);
    }

    private int mapStressCategoryToScore(String category) {
        switch (category.toUpperCase()) {
            case "SAFE": return 10;
            case "SEMI-CRITICAL": return 20;
            case "CRITICAL": return 30;
            case "OVER-EXPLOITED": return 40;
            default: return 0;
        }
    }

    private int mapScaleToScore(Double scaleMW) {
        if (scaleMW == null) return 0;
        if (scaleMW < 50) return 10;
        if (scaleMW <= 200) return 20;
        return 30;
    }

    private int mapDisclosureToScore(String sourceType) {
        if (sourceType == null) return 30;
        switch (sourceType.toUpperCase()) {
            case "ESG REPORT": return 0;
            case "PARTIAL": return 15;
            case "ESTIMATED": return 20;
            case "NOT DISCLOSED": return 30;
            default: return 30;
        }
    }

    private String mapTotalToCategory(int total) {
        if (total <= 30) return "LOW";
        if (total <= 60) return "MEDIUM";
        if (total <= 85) return "HIGH";
        return "CRITICAL";
    }
}