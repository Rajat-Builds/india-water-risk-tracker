package com.rajat.waterrisktracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "water_risk_scores")
public class WaterRiskScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_center_id", nullable = false)
    private DataCenter dataCenter;

    @Column(nullable = false)
    private Integer groundwaterStressScore;

    @Column(nullable = false)
    private Integer facilityScaleScore;

    @Column(nullable = false)
    private Integer disclosureTransparencyScore;

    @Column(nullable = false)
    private Integer totalScore;

    @Column(nullable = false)
    private String riskCategory;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    public WaterRiskScore() {
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

    public Integer getGroundwaterStressScore() {
        return groundwaterStressScore;
    }

    public void setGroundwaterStressScore(Integer groundwaterStressScore) {
        this.groundwaterStressScore = groundwaterStressScore;
    }

    public Integer getFacilityScaleScore() {
        return facilityScaleScore;
    }

    public void setFacilityScaleScore(Integer facilityScaleScore) {
        this.facilityScaleScore = facilityScaleScore;
    }

    public Integer getDisclosureTransparencyScore() {
        return disclosureTransparencyScore;
    }

    public void setDisclosureTransparencyScore(Integer disclosureTransparencyScore) {
        this.disclosureTransparencyScore = disclosureTransparencyScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
