package com.example.api.notification.application.port.in;

import com.example.api.notification.dto.NotificationDto;

public interface SaveNotificationUsecase {
    NotificationDto createNotification(NotificationDto notificationDto);
}