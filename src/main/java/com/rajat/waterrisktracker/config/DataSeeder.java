package com.rajat.waterrisktracker.config;

import com.rajat.waterrisktracker.entity.*;
import com.rajat.waterrisktracker.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DataCenterRepository dataCenterRepository;
    private final GroundWaterStressLevelRepository groundWaterStressLevelRepository;
    private final WaterUsageRecordRepository waterUsageRecordRepository;

    public DataSeeder(DataCenterRepository dataCenterRepository,
                       GroundWaterStressLevelRepository groundWaterStressLevelRepository,
                       WaterUsageRecordRepository waterUsageRecordRepository) {
        this.dataCenterRepository = dataCenterRepository;
        this.groundWaterStressLevelRepository = groundWaterStressLevelRepository;
        this.waterUsageRecordRepository = waterUsageRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dataCenterRepository.count() > 0) {
            System.out.println("Data already seeded, skipping DataSeeder.");
            return;
        }

        System.out.println("Seeding database with 10 data centers...");

        // ---- Data Centers ----
        DataCenter dc1 = newDataCenter("Yotta D1 Data Center", "Yotta Infrastructure", "Greater Noida",
                "Gautam Buddha Nagar", "Uttar Pradesh", 28.5355, 77.391, 28.8, "Operational");

        DataCenter dc2 = newDataCenter("AWS Hyderabad Region", "Amazon Web Services", "Hyderabad",
                "Rangareddy", "Telangana", 17.385, 78.4867, 200.0, "Operational");

        DataCenter dc3 = newDataCenter("Google Cloud AI Hub Visakhapatnam", "Google", "Visakhapatnam",
                "Visakhapatnam", "Andhra Pradesh", 17.6868, 83.2185, 1000.0, "Under Construction");

        DataCenter dc4 = newDataCenter("Microsoft Azure Pune (Pimpri-Chinchwad)", "Microsoft", "Pune",
                "Pune", "Maharashtra", 18.5204, 73.8567, 150.0, "Operational");

        DataCenter dc5 = newDataCenter("NTT Mumbai Data Center", "NTT DATA", "Mumbai",
                "Thane", "Maharashtra", 19.2183, 72.9781, 120.0, "Operational");

        DataCenter dc6 = newDataCenter("AdaniConneX Chennai Data Center", "Adani Connekt", "Chennai",
                "Thiruvallur", "Tamil Nadu", 13.1067, 80.0, 80.0, "Operational");

        DataCenter dc7 = newDataCenter("STT GDC Bangalore Data Center", "ST Telemedia Global Data Centres", "Bangalore",
                "Bangalore Urban", "Karnataka", 12.9716, 77.5946, 100.0, "Operational");

        DataCenter dc8 = newDataCenter("Meta-Sify Data Center", "Sify (for Meta)", "Visakhapatnam",
                "Visakhapatnam", "Andhra Pradesh", 17.69, 83.22, 500.0, "Under Construction");

        DataCenter dc9 = newDataCenter("TCS Data Center Network", "Tata Consultancy Services", "Mumbai",
                "Mumbai Suburban", "Maharashtra", 19.076, 72.8777, 90.0, "Operational");

        DataCenter dc10 = newDataCenter("Reliance Jio Data Center", "Reliance Jio", "Nagpur",
                "Nagpur", "Maharashtra", 21.1458, 79.0882, 60.0, "Operational");

        dataCenterRepository.saveAll(java.util.List.of(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10));

        // ---- Groundwater Stress Levels ----
        groundWaterStressLevelRepository.saveAll(java.util.List.of(
            newStressLevel("Gautam Buddha Nagar", "Uttar Pradesh", "SAFE", 2024,
                "Central Ground Water Board (district-level; Noida/Greater Noida urban pockets locally over-exploited)"),
            newStressLevel("Rangareddy", "Telangana", "CRITICAL", 2025,
                "Central Ground Water Board 2025 report (district has mixed mandals - some over-exploited, some semi-critical, some safe; CRITICAL used as district-representative label)"),
            newStressLevel("Visakhapatnam", "Andhra Pradesh", "SEMI-CRITICAL", 2026,
                "Mongabay-India report citing 2.12 TMC groundwater available as of April 2026 - lowest in the state"),
            newStressLevel("Pune", "Maharashtra", "SEMI-CRITICAL", 2025,
                "CEEW report - Maharashtra data center growth noted as favoring coastal/peri-urban clusters partly due to inland water stress concerns"),
            newStressLevel("Thane", "Maharashtra", "SAFE", 2024,
                "Central Ground Water Board - coastal Maharashtra districts generally show lower groundwater stress than inland districts"),
            newStressLevel("Thiruvallur", "Tamil Nadu", "CRITICAL", 2024,
                "Central Ground Water Board - Tamil Nadu coastal/peri-urban districts near Chennai widely reported as groundwater stressed"),
            newStressLevel("Bangalore Urban", "Karnataka", "OVER-EXPLOITED", 2024,
                "Central Ground Water Board - Bangalore Urban widely and repeatedly classified as over-exploited due to dense urbanization"),
            newStressLevel("Mumbai Suburban", "Maharashtra", "SAFE", 2024,
                "Central Ground Water Board - Mumbai Suburban district generally categorized as safe, consistent with coastal Maharashtra pattern"),
            newStressLevel("Nagpur", "Maharashtra", "SEMI-CRITICAL", 2025,
                "CEEW report - Maharashtra data center siting patterns note inland areas like Nagpur as comparatively water-stressed vs coastal clusters")
        ));

        // ---- Water Usage Records ----
        waterUsageRecordRepository.saveAll(java.util.List.of(
            newUsageRecord(dc1, 2020, 120000.0, "ESTIMATED",
                "https://www.downtoearth.org.in/science-technology/indias-digital-thirst-data-centres-are-rising-in-water-scarce-regions-and-locals-are-paying-the-price"),
            newUsageRecord(dc2, 2025, null, "PARTIAL",
                "https://www.itechpost.com/articles/236393/20260619/amazon-showcases-india-water-conservation-water-replenishment-projects-amid-data-center-expansion.htm"),
            newUsageRecord(dc3, 2026, null, "NOT DISCLOSED",
                "https://india.mongabay.com/2026/05/india-bets-on-data-centres-even-as-water-energy-use-concerns-mount/"),
            newUsageRecord(dc4, 2026, null, "PARTIAL",
                "https://local.microsoft.com/communities/asia-pacific/india-hyderabad-and-pune/"),
            newUsageRecord(dc5, 2025, null, "NOT DISCLOSED", null),
            newUsageRecord(dc6, 2025, null, "NOT DISCLOSED", null),
            newUsageRecord(dc7, 2025, null, "NOT DISCLOSED", null),
            newUsageRecord(dc8, 2025, null, "NOT DISCLOSED", null),
            newUsageRecord(dc9, 2025, null, "NOT DISCLOSED", null),
            newUsageRecord(dc10, 2025, null, "NOT DISCLOSED", null)
        ));

        System.out.println("Seeding complete.");
    }

    private DataCenter newDataCenter(String name, String company, String city, String district,
                                      String state, double lat, double lon, double scaleMW, String status) {
        DataCenter dc = new DataCenter();
        dc.setName(name);
        dc.setCompany(company);
        dc.setCity(city);
        dc.setDistrict(district);
        dc.setState(state);
        dc.setLatitude(lat);
        dc.setLongitude(lon);
        dc.setFacilityScaleMW(scaleMW);
        dc.setOperationalStatus(status);
        return dc;
    }

    private GroundWaterStressLevel newStressLevel(String district, String state, String category,
                                                    int year, String source) {
        GroundWaterStressLevel level = new GroundWaterStressLevel();
        level.setDistrict(district);
        level.setState(state);
        level.setStressCategory(category);
        level.setAssessmentYear(year);
        level.setSource(source);
        return level;
    }

    private WaterUsageRecord newUsageRecord(DataCenter dc, int year, Double liters, String sourceType, String url) {
        WaterUsageRecord record = new WaterUsageRecord();
        record.setDataCenter(dc);
        record.setYear(year);
        record.setReportedLitersPerDay(liters);
        record.setSourceType(sourceType);
        record.setSourceUrl(url);
        return record;
    }
}