package com.example.api.chat.domain;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long chatId;
    private UUID roomId;
    private Long senderId;
    private String content;
    private Boolean image;
    private Integer readCount;
}
