package com.example.api.fcm.applocation.port.out;

import org.springframework.stereotype.Component;

@Component
public interface FindFcmTokenPort {
    String findUserFcmToken(Long userId);
}