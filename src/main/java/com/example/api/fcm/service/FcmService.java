package com.example.api.fcm.service;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.fcm.application.port.in.SendNotificationUsecase;
import com.example.api.fcm.application.port.out.FindFcmTokenPort;
import com.example.api.fcm.dto.FcmDto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmService implements SendNotificationUsecase {
    private final FindFcmTokenPort findFcmTokenPort;
    
    @Autowired
    private FirebaseApp firebaseApp;
    
    @Override
    @Transactional
    public void sendNotification(FcmDto fcmDto) {
        String fcmToken = findFcmTokenPort.findUserFcmToken(fcmDto.getUserId());
        if (fcmToken == null) {
            log.error("FcmService::sendNotification: User FCM token not found");
            throw new CustomException(ErrorCodeEnum.FCM_TOKEN_NOT_FOUND);
        }
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle((fcmDto.getMatchingId() == null ? "" : "[" + fcmDto.getMatchingId() + "]") + fcmDto.getTitle())
                        .setBody(fcmDto.getBody())
                        .build())
                .setToken(fcmToken)
                .build();
        try {
            FirebaseMessaging.getInstance(firebaseApp).send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("FcmService::sendNotification: Cannot send notification.");
        }
    }
}