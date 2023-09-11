package com.example.api.notification.dto;

import com.example.api.notification.type.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long notificationId;
    
    @NotNull
    private NotificationTypeEnum type;
    
    @NotBlank
    private String content;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    @NotNull
    private Boolean isActive;
}