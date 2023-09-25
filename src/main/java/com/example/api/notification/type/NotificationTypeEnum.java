package com.example.api.notification.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum NotificationTypeEnum {
    Temp("temp", 1);
    
    private final String notificationType;
    private final Integer notificationTypeCode;
}