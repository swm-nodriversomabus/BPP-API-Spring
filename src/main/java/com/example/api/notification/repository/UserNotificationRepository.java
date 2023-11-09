package com.example.api.notification.repository;

import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;
import com.example.api.notification.adapter.out.persistence.UserNotificationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserNotificationRepository extends JpaRepository<UserNotificationEntity, UserNotificationPK> {
    Optional<UserNotificationEntity> getByUserIdAndNotificationId(UUID userId, Long notificationId);
    List<UserNotificationEntity> getByUserId(UUID userId);
}