package com.example.api.notification.application.port.in;

import com.example.api.notification.dto.NotificationDto;

import java.util.List;
import java.util.Optional;

public interface FindNotificationUsecase {
    Optional<NotificationDto> getNotificationById(Long notificationId);
    List<NotificationDto> getUserNotificationList(Long userId);
}