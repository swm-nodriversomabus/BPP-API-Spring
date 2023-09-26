package com.example.api.notification.repository;

import com.example.api.notification.adapter.out.persistence.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    Optional<NotificationEntity> getNotificationEntityByNotificationId(Long notificationId);
}