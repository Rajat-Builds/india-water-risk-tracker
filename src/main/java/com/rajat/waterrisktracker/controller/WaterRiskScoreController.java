package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.entity.WaterRiskScore;
import com.rajat.waterrisktracker.service.AiSummaryService;
import com.rajat.waterrisktracker.service.WaterRiskScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/waterriskscores")
public class WaterRiskScoreController {

    @Autowired
    private WaterRiskScoreService waterRiskScoreService;

    @Autowired
    private AiSummaryService aiSummaryService;

    @GetMapping
    public List<WaterRiskScore> getAllScores() {
        return waterRiskScoreService.getAllScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterRiskScore> getScoreById(@PathVariable Long id) {
        WaterRiskScore score = waterRiskScoreService.getScoreById(id);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }

    @PostMapping("/calculate/{dataCenterId}")
    public ResponseEntity<WaterRiskScore> calculateRiskScore(@PathVariable Long dataCenterId) {
        WaterRiskScore score = waterRiskScoreService.calculateRiskScore(dataCenterId);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }

    @GetMapping("/summary/{dataCenterId}")
    public ResponseEntity<Map<String, String>> getAiSummary(@PathVariable Long dataCenterId) {
        WaterRiskScore score = waterRiskScoreService.getLatestScoreForDataCenter(dataCenterId);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }

        String summary = aiSummaryService.generateSummary(
            score.getDataCenter().getName(),
            score.getDataCenter().getCompany(),
            score.getTotalScore(),
            score.getRiskCategory(),
            score.getGroundwaterStressScore(),
            score.getFacilityScaleScore(),
            score.getDisclosureTransparencyScore()
        );

        return ResponseEntity.ok(Map.of("summary", summary));
    }
}