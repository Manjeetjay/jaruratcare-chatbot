package com.whatsappbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message_logs")
public class MessageLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String originalMessage;
    private String generatedReply;
    private String timestamp;

    public MessageLog(String userId, String originalMessage, String generatedReply, String timestamp) {
        this.userId = userId;
        this.originalMessage = originalMessage;
        this.generatedReply = generatedReply;
        this.timestamp = timestamp;
    }
}
