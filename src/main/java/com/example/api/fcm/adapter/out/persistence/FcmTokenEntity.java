package com.example.api.fcm.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FcmTokenPK.class)
@Table(name="fcmToken")
public class FcmTokenEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    @Column(nullable = false)
    private String fcmToken;
}