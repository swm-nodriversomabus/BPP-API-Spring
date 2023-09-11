package com.example.api.notification.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserNotificationPK implements Serializable {
    private Long userId;
    private Long notificationId;
}