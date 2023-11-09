package com.example.api.fcm.application.port.out;

import com.example.api.fcm.domain.FcmToken;
import org.springframework.stereotype.Component;

@Component
public interface SaveFcmTokenPort {
    FcmToken saveFcmToken(FcmToken fcmToken);
}