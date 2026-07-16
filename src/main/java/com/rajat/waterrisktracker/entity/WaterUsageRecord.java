package com.rajat.waterrisktracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "water_usage_records")
public class WaterUsageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_center_id", nullable = false)
    private DataCenter dataCenter;

    @Column(nullable = false)
    private Integer year;

    private Double reportedLitersPerDay;

    @Column(nullable = false)
    private String sourceType;

    private String sourceUrl;

    public WaterUsageRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getReportedLitersPerDay() {
        return reportedLitersPerDay;
    }

    public void setReportedLitersPerDay(Double reportedLitersPerDay) {
        this.reportedLitersPerDay = reportedLitersPerDay;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
