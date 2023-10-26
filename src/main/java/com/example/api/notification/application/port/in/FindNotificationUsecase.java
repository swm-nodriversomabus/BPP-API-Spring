package com.example.api.notification.application.port.in;

import com.example.api.notification.dto.FindNotificationDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindNotificationUsecase {
    Optional<FindNotificationDto> getNotificationById(Long notificationId);
    List<FindNotificationDto> getUserNotificationList(UUID userId);
}