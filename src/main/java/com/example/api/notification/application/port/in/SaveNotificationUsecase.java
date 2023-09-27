package com.example.api.notification.application.port.in;

import com.example.api.notification.dto.FindNotificationDto;
import com.example.api.notification.dto.SaveNotificationDto;

public interface SaveNotificationUsecase {
    FindNotificationDto createNotification(SaveNotificationDto saveNotificationDto);
}