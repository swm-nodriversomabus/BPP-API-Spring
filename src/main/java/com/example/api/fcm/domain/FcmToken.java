package com.example.api.fcm.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FcmToken {
    private UUID userId;
    private String fcmToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}