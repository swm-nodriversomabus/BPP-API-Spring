package com.example.api.notification.repository;

import com.example.api.notification.adapter.out.persistence.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    Optional<NotificationEntity> getNotificationEntityByNotificationId(Long notificationId);
}