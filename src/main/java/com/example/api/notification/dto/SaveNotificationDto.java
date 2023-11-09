package com.example.api.notification.dto;

import com.example.api.notification.type.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveNotificationDto {
    @NotNull
    private NotificationTypeEnum type;
    
    @Builder.Default
    private Long matchingId = 0L;
    
    @NotBlank
    private String content;

    @NotNull
    private Boolean isActive;
}