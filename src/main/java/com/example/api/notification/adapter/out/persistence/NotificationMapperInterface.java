package com.example.api.notification.adapter.out.persistence;

import com.example.api.notification.domain.Notification;
import com.example.api.notification.dto.NotificationDto;
import com.example.api.notification.dto.UserNotificationDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapperInterface {
    Notification toDomain(NotificationDto notificationDto);
    NotificationEntity toEntity(Notification notification);
    UserNotificationEntity toEntity(UserNotificationDto userNotificationDto);
    Notification toDomain(NotificationEntity notificationEntity);
    NotificationDto toDto(Notification notification);
    UserNotificationDto toDto(UserNotificationEntity userNotificationEntity);
}