package com.example.api.notification.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum NotificationTypeEnum {
    NewApplication("새로운 매칭 신청", 0),
    ApplicationApproved("매칭 신청 수락", 1),
    ApplicationDeclined("매칭 신청 거절", 2);
    
    private final String notificationType;
    private final Integer notificationTypeCode;
}