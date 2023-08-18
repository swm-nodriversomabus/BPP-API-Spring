package com.example.api.preference.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.preference.dto.UserPreferenceDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Long userId;
    
    @Id
    private Long preferenceId;
    
    public UserPreferenceDto toDto() {
        return UserPreferenceDto.builder()
                .userId(userId)
                .preferenceId(preferenceId)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}