package com.whatsappbot.controller;

import com.whatsappbot.dto.WebhookRequest;
import com.whatsappbot.dto.WebhookResponse;
import com.whatsappbot.model.MessageLog;
import com.whatsappbot.service.ChatbotService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final ChatbotService chatbotService;


    @PostMapping
    public WebhookResponse receiveMessage(@RequestBody WebhookRequest request) {
        String reply = chatbotService.processMessage(request.getUserId(), request.getMessage());
        return new WebhookResponse(reply, Instant.now().toString());
    }

    @GetMapping("/logs")
    public List<MessageLog> getLogs() {
        return chatbotService.getLogs();
    }
}
