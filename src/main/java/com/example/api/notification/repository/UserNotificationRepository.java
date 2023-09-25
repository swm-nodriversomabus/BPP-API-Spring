package com.example.api.notification.repository;

import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;
import com.example.api.notification.adapter.out.persistence.UserNotificationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNotificationRepository extends JpaRepository<UserNotificationEntity, UserNotificationPK> {
    Optional<UserNotificationEntity> getUserNotificationEntityByUserIdAndNotificationId(Long userId, Long notificationId);
    List<UserNotificationEntity> getUserNotificationEntitiesByUserId(Long userId);
}