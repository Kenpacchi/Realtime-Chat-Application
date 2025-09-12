package com.chatApp.chat.App_backend.entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String sender;

    private String content;

    private LocalDateTime time;

    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;
        this.time = LocalDateTime.now();
    }
}
