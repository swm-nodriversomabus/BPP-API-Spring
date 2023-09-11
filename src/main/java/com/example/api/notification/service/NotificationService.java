package com.example.api.notification.service;

import com.example.api.notification.adapter.out.persistence.NotificationEntity;
import com.example.api.notification.adapter.out.persistence.NotificationMapperInterface;
import com.example.api.notification.adapter.out.persistence.UserNotificationEntity;
import com.example.api.notification.application.port.in.FindNotificationUsecase;
import com.example.api.notification.application.port.in.SaveNotificationUsecase;
import com.example.api.notification.application.port.out.FindNotificationPort;
import com.example.api.notification.application.port.out.SaveNotificationPort;
import com.example.api.notification.domain.Notification;
import com.example.api.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService implements SaveNotificationUsecase, FindNotificationUsecase {
    private final NotificationMapperInterface notificationMapper;
    private final SaveNotificationPort saveNotificationPort;
    private final FindNotificationPort findNotificationPort;
    
    @Override
    @Transactional
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = saveNotificationPort.createNotification(notificationMapper.toDomain(notificationDto));
        return notificationMapper.toDto(notification);
    }
    
    @Override
    public Optional<NotificationDto> getNotificationById(Long notificationId) {
        return findNotificationPort.getNotificationById(notificationId)
                .map(NotificationEntity::toDto);
    }
    
    @Override
    public List<NotificationDto> getUserNotificationList(Long userId) {
        List<UserNotificationEntity> notificationPairList = findNotificationPort.getUserNotificationList(userId);
        List<NotificationDto> userNotificationList = new ArrayList<>();
        for (UserNotificationEntity notificationPair: notificationPairList) {
            NotificationEntity notificationEntity = findNotificationPort.getNotificationById(notificationPair.getNotificationId()).orElseThrow();
            userNotificationList.add(notificationEntity.toDto());
        }
        return userNotificationList;
    }
}