package com.example.api.fcm.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FcmToken {
    private Long userId;
    private String fcmToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}