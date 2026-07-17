package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.entity.DataCenter;
import com.rajat.waterrisktracker.service.DataCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datacenters")
public class DataCenterController {

    @Autowired
    private DataCenterService dataCenterService;

    @GetMapping
    public List<DataCenter> getAllDataCenters() {
        return dataCenterService.getAllDataCenters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataCenter> getDataCenterById(@PathVariable Long id) {
        DataCenter dataCenter = dataCenterService.getDataCenterById(id);
        if (dataCenter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dataCenter);
    }

    @PostMapping
    public DataCenter createDataCenter(@RequestBody DataCenter dataCenter) {
        return dataCenterService.createDataCenter(dataCenter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataCenter> updateDataCenter(@PathVariable Long id, @RequestBody DataCenter dataCenter) {
        DataCenter updated = dataCenterService.updateDataCenter(id, dataCenter);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataCenter(@PathVariable Long id) {
        boolean deleted = dataCenterService.deleteDataCenter(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}