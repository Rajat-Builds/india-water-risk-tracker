package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.entity.WaterRiskScore;
import com.rajat.waterrisktracker.service.WaterRiskScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waterriskscores")
public class WaterRiskScoreController {

    @Autowired
    private WaterRiskScoreService waterRiskScoreService;

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
}
