package com.example.api.notification.dto;

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
public class UserNotificationDto {
    private Long sequenceId;
    
    @NotNull
    private UUID userId;
    
    @NotNull
    private Long notificationId;
    
    @NotNull
    private Boolean isRead;
    
    private LocalDateTime readAt;
}