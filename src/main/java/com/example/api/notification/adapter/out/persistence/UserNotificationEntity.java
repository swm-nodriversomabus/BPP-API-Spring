package com.example.api.notification.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userNotification")
public class UserNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequenceId;
    
    @Column(nullable = false)
    private UUID userId;
    
    @Column(nullable = false)
    private Long notificationId;
    
    @Column(nullable = false)
    private Boolean isRead;
    
    private LocalDateTime readAt;
}