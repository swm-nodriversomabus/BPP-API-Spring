package com.example.api.chat.domain;

import com.example.api.user.domain.ChatUser;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Chat {
    private UUID roomId;
    private ChatUser senderId;
    private String content;
    private Boolean image;
    private Integer readCount;
    private LocalDateTime createdAt;
}