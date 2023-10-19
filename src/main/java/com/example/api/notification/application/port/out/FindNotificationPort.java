package com.example.api.notification.application.port.out;

import com.example.api.notification.adapter.out.persistence.NotificationEntity;
import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindNotificationPort {
    Optional<NotificationEntity> getNotificationById(Long notificationId);
    List<UserNotificationEntity> getUserNotificationList(UUID userId);
}