package com.example.api.notification.application.port.out;

import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;

public interface UserNotificationPort {
    UserNotificationEntity createUserNotification(UserNotificationEntity userNotificationEntity);
    UserNotificationEntity getUserNotificationEntity(UserNotificationEntity userNotificationEntity);
}