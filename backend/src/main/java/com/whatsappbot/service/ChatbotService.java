package com.whatsappbot.service;

import com.whatsappbot.model.MessageLog;
import com.whatsappbot.repository.MessageLogRepository;

import lombok.RequiredArgsConstructor;


import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final MessageLogRepository messageLogRepository;


    public String processMessage(String userId, String inputMessage) {
        String cleanedMessage = inputMessage.trim().toLowerCase();
        String reply;

        if (cleanedMessage.contains("hi") || cleanedMessage.contains("hello") || cleanedMessage.contains("hey")) {
            reply = "Hello";
        } else if (cleanedMessage.contains("bye") || cleanedMessage.contains("goodbye")) {
            reply = "Goodbye";
        } else if (cleanedMessage.contains("help")) {
            reply = "I can say hello and goodbye. Just try chatting with me!";
        } else {
            reply = "I am a simple bot. I didn't understand that. You can say 'hi' or 'bye'.";
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        MessageLog log = new MessageLog(userId, inputMessage, reply, Instant.now().toString());
        messageLogRepository.save(log);

        return reply;
    }

    public List<MessageLog> getLogs() {
        return messageLogRepository.findAll();
    }
}
