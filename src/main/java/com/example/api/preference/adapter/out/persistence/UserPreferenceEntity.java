package com.example.api.preference.adapter.out.persistence;

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
@IdClass(UserPreferencePK.class)
@Table(name="userPreference")
public class UserPreferenceEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    @Id
    private Long preferenceId;
}