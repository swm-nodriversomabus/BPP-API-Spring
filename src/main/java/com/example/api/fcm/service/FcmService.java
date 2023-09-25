package com.example.api.fcm.service;

import com.example.api.fcm.applocation.port.out.FindFcmTokenPort;
import com.example.api.fcm.dto.FcmDto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmService {
    private final FindFcmTokenPort findFcmTokenPort;
    @Autowired
    private FirebaseApp firebaseApp;
    
    public void sendNotification(FcmDto fcmDto) {
        String fcmToken = findFcmTokenPort.findUserFcmToken(fcmDto.getUserId());
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(fcmDto.getTitle())
                        .setBody(fcmDto.getBody())
                        .build())
                .setToken(fcmToken)
                .build();
        try {
            FirebaseMessaging.getInstance(firebaseApp).send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("Cannot send notification.");
        }
    }
}