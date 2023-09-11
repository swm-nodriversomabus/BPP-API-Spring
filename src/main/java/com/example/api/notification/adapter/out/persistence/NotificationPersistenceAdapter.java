package com.example.api.notification.adapter.out.persistence;

import com.example.api.notification.application.port.out.FindNotificationPort;
import com.example.api.notification.application.port.out.SaveNotificationPort;
import com.example.api.notification.application.port.out.UserNotificationPort;
import com.example.api.notification.domain.Notification;
import com.example.api.notification.repository.NotificationRepository;
import com.example.api.notification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ComponentScan
public class NotificationPersistenceAdapter implements SaveNotificationPort, FindNotificationPort, UserNotificationPort {
    private final NotificationMapperInterface notificationMapper;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    
    @Override
    public Notification createNotification(Notification notification) {
        NotificationEntity notificationEntity = notificationRepository.save(notificationMapper.toEntity(notification));
        return notificationMapper.toDomain(notificationEntity);
    }
    
    @Override
    public UserNotificationEntity createUserNotification(UserNotificationEntity userNotificationEntity) {
        return userNotificationRepository.save(userNotificationEntity);
    }
    
    @Override
    public Optional<NotificationEntity> getNotificationById(Long notificationId) {
        return notificationRepository.getNotificationEntityByNotificationId(notificationId);
    }
    
    @Override
    public List<UserNotificationEntity> getUserNotificationList(Long userId) {
        return userNotificationRepository.getUserNotificationEntitiesByUserId(userId);
    }
    
    @Override
    public UserNotificationEntity getUserNotificationEntity(UserNotificationEntity userNotificationEntity) {
        return userNotificationRepository.getUserNotificationEntityByUserIdAndNotificationId(userNotificationEntity.getUserId(), userNotificationEntity.getNotificationId()).orElseThrow();
    }
}