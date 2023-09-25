package com.example.api.notification.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.notification.dto.FindNotificationDto;
import com.example.api.notification.dto.SaveNotificationDto;
import com.example.api.notification.type.NotificationTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notification")
public class NotificationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    
    @Column(nullable = false)
    private NotificationTypeEnum type;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    public FindNotificationDto toDto() {
        return FindNotificationDto.builder()
                .notificationId(notificationId)
                .type(type)
                .content(content)
                .isActive(isActive)
                .build();
    }
}