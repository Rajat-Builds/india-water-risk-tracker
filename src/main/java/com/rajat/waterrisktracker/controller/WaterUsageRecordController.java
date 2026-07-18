package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.entity.WaterUsageRecord;
import com.rajat.waterrisktracker.service.WaterUsageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waterusagerecords")
public class WaterUsageRecordController {

    @Autowired
    private WaterUsageRecordService waterUsageRecordService;

    @GetMapping
    public List<WaterUsageRecord> getAllRecords() {
        return waterUsageRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterUsageRecord> getRecordById(@PathVariable Long id) {
        WaterUsageRecord record = waterUsageRecordService.getRecordById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public WaterUsageRecord createRecord(@RequestBody WaterUsageRecord record) {
        return waterUsageRecordService.createRecord(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterUsageRecord> updateRecord(@PathVariable Long id, @RequestBody WaterUsageRecord record) {
        WaterUsageRecord updated = waterUsageRecordService.updateRecord(id, record);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        boolean deleted = waterUsageRecordService.deleteRecord(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}