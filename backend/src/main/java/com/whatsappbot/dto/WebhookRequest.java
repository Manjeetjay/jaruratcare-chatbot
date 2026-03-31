package com.whatsappbot.dto;

import lombok.Data;

@Data
public class WebhookRequest {
    private String userId;
    private String message;
}
