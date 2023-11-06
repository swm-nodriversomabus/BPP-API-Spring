package com.example.api.user.adapter.out.persistence;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProfileImagePK.class)
@Table(name="profileImage")
public class ProfileImageEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    private String profileImage;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStateEnum state;
}