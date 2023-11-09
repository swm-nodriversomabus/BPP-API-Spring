package com.example.api.notification.application.port.out;

import com.example.api.notification.domain.Notification;

public interface SaveNotificationPort {
    Notification createNotification(Notification notification);
}