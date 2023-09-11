package com.example.api.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDto {
    private Long sequenceId;
    
    @NotNull
    private Long userId;
    
    @NotNull
    private Long notificationId;
    
    @NotNull
    private Boolean isRead;
    
    private LocalDateTime readAt;
}