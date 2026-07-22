package com.rajat.waterrisktracker.controller;

import com.rajat.waterrisktracker.dto.ChatRequest;
import com.rajat.waterrisktracker.dto.ChatResponse;
import com.rajat.waterrisktracker.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = chatService.answerQuestion(request);
        return new ChatResponse(answer);
    }
}