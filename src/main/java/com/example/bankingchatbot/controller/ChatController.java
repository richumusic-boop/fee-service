package com.example.bankingchatbot.controller;

import com.example.bankingchatbot.service.ChatbotService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatbotService chatbotService;

    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public Map<String, String> processMessage(@RequestBody Map<String, String> request) {
        String userMessage = request.getOrDefault("message", "");
        String reply = chatbotService.handleMessage(userMessage);
        return Map.of("reply", reply);
    }
}
