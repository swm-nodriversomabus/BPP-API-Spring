package com.example.api.chat.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {
    private UUID roomId;
    private Long senderId;
    private String content;
    private Boolean image;
    private Integer readCount;
}
