package com.example.api.fcm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FcmTokenDto {
    @NotNull
    private UUID userId;
    
    @NotNull
    private String fcmToken;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}