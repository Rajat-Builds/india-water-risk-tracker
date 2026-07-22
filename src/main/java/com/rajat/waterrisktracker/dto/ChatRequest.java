package com.rajat.waterrisktracker.dto;

import java.util.List;

public class ChatRequest {
    private String question;
    private Long dataCenterId;       // nullable — null means no facility selected
    private List<ChatMessage> conversationHistory;

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Long getDataCenterId() { return dataCenterId; }
    public void setDataCenterId(Long dataCenterId) { this.dataCenterId = dataCenterId; }

    public List<ChatMessage> getConversationHistory() { return conversationHistory; }
    public void setConversationHistory(List<ChatMessage> conversationHistory) { this.conversationHistory = conversationHistory; }
}