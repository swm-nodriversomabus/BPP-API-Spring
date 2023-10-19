package com.example.api.notification.adapter.out.persistence;

import com.example.api.notification.domain.Notification;
import com.example.api.notification.dto.FindNotificationDto;
import com.example.api.notification.dto.SaveNotificationDto;
import com.example.api.notification.dto.UserNotificationDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapperInterface {
    Notification toDomain(SaveNotificationDto saveNotificationDto);
    NotificationEntity toEntity(Notification notification);
    UserNotificationEntity toEntity(UserNotificationDto userNotificationDto);
    Notification toDomain(NotificationEntity notificationEntity);
    FindNotificationDto toDto(Notification notification);
    FindNotificationDto toDto(NotificationEntity notificationEntity);
    UserNotificationDto toDto(UserNotificationEntity userNotificationEntity);
}