package com.rajat.waterrisktracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ground_water_stress_level")
public class GroundWaterStressLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String stressCategory;

    @Column(nullable = false)
    private Integer assessmentYear;

    private String source;

    public GroundWaterStressLevel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStressCategory() {
        return stressCategory;
    }

    public void setStressCategory(String stressCategory) {
        this.stressCategory = stressCategory;
    }

    public Integer getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(Integer assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
