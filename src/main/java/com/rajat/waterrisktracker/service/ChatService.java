package com.rajat.waterrisktracker.service;

import com.rajat.waterrisktracker.dto.ChatMessage;
import com.rajat.waterrisktracker.dto.ChatRequest;
import com.rajat.waterrisktracker.entity.DataCenter;
import com.rajat.waterrisktracker.entity.WaterRiskScore;
import com.rajat.waterrisktracker.repository.DataCenterRepository;
import com.rajat.waterrisktracker.repository.WaterRiskScoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();

    private final DataCenterRepository dataCenterRepository;
    private final WaterRiskScoreRepository waterRiskScoreRepository;

    public ChatService(DataCenterRepository dataCenterRepository,
                        WaterRiskScoreRepository waterRiskScoreRepository) {
        this.dataCenterRepository = dataCenterRepository;
        this.waterRiskScoreRepository = waterRiskScoreRepository;
    }

    public String answerQuestion(ChatRequest request) {
        String contextBlock = buildContextBlock(request.getDataCenterId());

        List<Map<String, Object>> contents = new ArrayList<>();

        contents.add(Map.of(
        "role", "user",
        "parts", new Object[]{ Map.of("text",
                "You are a water-risk assistant for the India Data Center Water Risk Tracker. " +
                "Answer questions in plain, concise English for a non-technical reader (journalist/regulator). " +
                "Formatting rules: do NOT use Markdown syntax like asterisks or bold markers. " +
                "Write in short plain sentences or short paragraphs, using actual line breaks between distinct points. " +
                "If listing multiple items (like a risk breakdown), put each item on its own line starting with a dash, " +
                "not asterisks or bullet symbols. " +
                "Here is the relevant context:\n\n" + contextBlock) }
));
        contents.add(Map.of(
                "role", "model",
                "parts", new Object[]{ Map.of("text",
                        "Understood, I'll use that context to answer questions clearly and concisely.") }
        ));

        if (request.getConversationHistory() != null) {
            for (ChatMessage msg : request.getConversationHistory()) {
                String geminiRole = "assistant".equalsIgnoreCase(msg.getRole()) ? "model" : "user";
                contents.add(Map.of(
                        "role", geminiRole,
                        "parts", new Object[]{ Map.of("text", msg.getContent()) }
                ));
            }
        }

        contents.add(Map.of(
                "role", "user",
                "parts", new Object[]{ Map.of("text", request.getQuestion()) }
        ));

        Map<String, Object> requestBody = Map.of("contents", contents);

        try {
            Map response = webClient.post()
                    .uri("/v1beta/models/gemini-flash-latest:generateContent?key=" + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            var candidates = (List<Map>) response.get("candidates");
            var content = (Map) candidates.get(0).get("content");
            var parts = (List<Map>) content.get("parts");
            return (String) parts.get(0).get("text");

        } catch (org.springframework.web.reactive.function.client.WebClientResponseException e) {
            System.out.println("GEMINI ERROR RESPONSE: " + e.getResponseBodyAsString());
            return "Sorry, I couldn't process that question right now.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I couldn't process that question right now.";
        }
    }

    private String buildContextBlock(Long dataCenterId) {
        if (dataCenterId == null) {
            return "No specific facility is currently selected. Answer generally across all 10 tracked " +
                   "Indian data centers if asked, or ask the user to specify a facility for detailed numbers.";
        }

        DataCenter dc = dataCenterRepository.findById(dataCenterId).orElse(null);
        if (dc == null) {
            return "The referenced facility could not be found. Answer generally instead.";
        }

        List<WaterRiskScore> scores =
                waterRiskScoreRepository.findByDataCenterIdOrderByCalculatedAtDesc(dataCenterId);

        if (scores.isEmpty()) {
            return String.format(
                "Facility: %s, operated by %s, located in %s, %s. No risk score has been calculated yet.",
                dc.getName(), dc.getCompany(), dc.getCity(), dc.getState()
            );
        }

        WaterRiskScore latest = scores.get(0);

        return String.format(
            "Facility: %s, operated by %s, located in %s, %s. " +
            "Total water risk score: %d/100, category: %s. " +
            "Breakdown - groundwater stress: %d/40, facility scale: %d/30, disclosure transparency: %d/30 " +
            "(higher disclosure score means less transparency).",
            dc.getName(), dc.getCompany(), dc.getCity(), dc.getState(),
            latest.getTotalScore(), latest.getRiskCategory(),
            latest.getGroundwaterStressScore(), latest.getFacilityScaleScore(), latest.getDisclosureTransparencyScore()
        );
    }
}