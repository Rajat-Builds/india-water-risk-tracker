package com.rajat.waterrisktracker.service;

import com.rajat.waterrisktracker.entity.WaterUsageRecord;
import com.rajat.waterrisktracker.repository.WaterUsageRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterUsageRecordService {

    @Autowired
    private WaterUsageRecordRepository waterUsageRecordRepository;

    public List<WaterUsageRecord> getAllRecords() {
        return waterUsageRecordRepository.findAll();
    }

    public WaterUsageRecord getRecordById(Long id) {
        return waterUsageRecordRepository.findById(id).orElse(null);
    }

    public WaterUsageRecord createRecord(WaterUsageRecord record) {
        return waterUsageRecordRepository.save(record);
    }

    public WaterUsageRecord updateRecord(Long id, WaterUsageRecord updatedRecord) {
        WaterUsageRecord existing = waterUsageRecordRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setDataCenter(updatedRecord.getDataCenter());
        existing.setYear(updatedRecord.getYear());
        existing.setReportedLitersPerDay(updatedRecord.getReportedLitersPerDay());
        existing.setSourceType(updatedRecord.getSourceType());
        existing.setSourceUrl(updatedRecord.getSourceUrl());
        return waterUsageRecordRepository.save(existing);
    }

    public boolean deleteRecord(Long id) {
        if (!waterUsageRecordRepository.existsById(id)) {
            return false;
        }
        waterUsageRecordRepository.deleteById(id);
        return true;
    }
}