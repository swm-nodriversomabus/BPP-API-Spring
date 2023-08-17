package com.example.api.chat.domain;

import com.example.api.user.domain.ChatUser;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {
    private UUID roomId;
    private ChatUser senderId;
    private String content;
    private Boolean image;
    private Integer readCount;
}
