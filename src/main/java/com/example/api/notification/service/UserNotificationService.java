package com.example.api.notification.service;

import com.example.api.notification.adapter.out.persistence.NotificationMapperInterface;
import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;
import com.example.api.notification.application.port.in.UserNotificationUsecase;
import com.example.api.notification.application.port.out.UserNotificationPort;
import com.example.api.notification.dto.UserNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserNotificationService implements UserNotificationUsecase {
    private final NotificationMapperInterface notificationMapper;
    private final UserNotificationPort userNotificationPort;
    
    @Override
    @Transactional
    public UserNotificationDto createUserNotification(UserNotificationDto userNotificationDto) {
        UserNotificationEntity userNotificationEntity =  userNotificationPort.createUserNotification(notificationMapper.toEntity(userNotificationDto));
        return notificationMapper.toDto(userNotificationEntity);
    }
    
    @Override
    @Transactional
    public void toggleReadState(UserNotificationDto userNotificationDto) {
        userNotificationDto = notificationMapper.toDto(userNotificationPort.getUserNotificationEntity(notificationMapper.toEntity(userNotificationDto)));
        userNotificationDto.setIsRead(!userNotificationDto.getIsRead());
        userNotificationPort.createUserNotification(notificationMapper.toEntity(userNotificationDto));
    }
}