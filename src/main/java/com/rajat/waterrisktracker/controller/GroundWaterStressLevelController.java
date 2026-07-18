package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.entity.GroundWaterStressLevel;
import com.rajat.waterrisktracker.service.GroundwaterStressLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groundwaterstresslevels")
public class GroundWaterStressLevelController {

    @Autowired
    private GroundwaterStressLevelService groundwaterStressLevelService;

    @GetMapping
    public List<GroundWaterStressLevel> getAllLevels() {
        return groundwaterStressLevelService.getAllLevels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroundWaterStressLevel> getLevelById(@PathVariable Long id) {
        GroundWaterStressLevel level = groundwaterStressLevelService.getLevelById(id);
        if (level == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(level);
    }

    @PostMapping
    public GroundWaterStressLevel createLevel(@RequestBody GroundWaterStressLevel level) {
        return groundwaterStressLevelService.createLevel(level);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroundWaterStressLevel> updateLevel(@PathVariable Long id, @RequestBody GroundWaterStressLevel level) {
        GroundWaterStressLevel updated = groundwaterStressLevelService.updateLevel(id, level);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        boolean deleted = groundwaterStressLevelService.deleteLevel(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}