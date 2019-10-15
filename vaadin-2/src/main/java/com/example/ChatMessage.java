package com.example;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {

    private String from;
    private LocalDateTime time;
    private String message;

    public ChatMessage(String from, String message) {
        this.from = from;
        this.message = message;
        this.time = LocalDateTime.now();
    }
}
