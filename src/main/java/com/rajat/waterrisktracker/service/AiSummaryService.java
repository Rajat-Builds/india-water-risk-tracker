package com.rajat.waterrisktracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AiSummaryService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();

    public String generateSummary(String dataCenterName, String company, int totalScore,
                                    String riskCategory, int groundwaterScore,
                                    int facilityScaleScore, int disclosureScore) {

        String prompt = String.format(
            "In 2-3 short sentences, explain in plain English why %s (operated by %s) " +
            "has a water risk score of %d out of 100, categorized as %s. " +
            "Breakdown: groundwater stress contributed %d/40, facility scale contributed %d/30, " +
            "and disclosure transparency contributed %d/30 (higher disclosure score means less transparency). " +
            "Write it for a non-technical reader like a journalist or regulator.",
            dataCenterName, company, totalScore, riskCategory,
            groundwaterScore, facilityScaleScore, disclosureScore
        );

        Map<String, Object> requestBody = Map.of(
            "contents", new Object[]{
                Map.of("parts", new Object[]{ Map.of("text", prompt) })
            }
        );

        try {
            Map response = webClient.post()
                  .uri("/v1beta/models/gemini-flash-latest:generateContent?key=" + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            var candidates = (java.util.List<Map>) response.get("candidates");
            var content = (Map) candidates.get(0).get("content");
            var parts = (java.util.List<Map>) content.get("parts");
            return (String) parts.get(0).get("text");

        } 
        
        catch (org.springframework.web.reactive.function.client.WebClientResponseException e) {
    System.out.println("GEMINI ERROR RESPONSE: " + e.getResponseBodyAsString());
    return "Summary temporarily unavailable.";
        }
        catch (Exception e) {
             e.printStackTrace();
            return "Summary temporarily unavailable.";
        }
    }
}