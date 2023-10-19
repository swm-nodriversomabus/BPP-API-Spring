package com.example.api.notification.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserNotificationPK implements Serializable {
    private UUID userId;
    private Long notificationId;
}