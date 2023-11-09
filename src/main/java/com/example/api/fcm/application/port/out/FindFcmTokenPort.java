package com.example.api.fcm.application.port.out;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface FindFcmTokenPort {
    String findUserFcmToken(UUID userId);
}