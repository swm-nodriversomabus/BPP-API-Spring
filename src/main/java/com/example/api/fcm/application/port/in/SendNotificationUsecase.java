package com.example.api.fcm.application.port.in;

import com.example.api.fcm.dto.FcmDto;

public interface SendNotificationUsecase {
    void sendNotification(FcmDto fcmDto);
}