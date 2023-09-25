package com.example.api.notification.application.port.in;

import com.example.api.notification.dto.UserNotificationDto;

public interface UserNotificationUsecase {
    UserNotificationDto createUserNotification(UserNotificationDto userNotificationDto);
    void toggleReadState(UserNotificationDto userNotificationDto);
}