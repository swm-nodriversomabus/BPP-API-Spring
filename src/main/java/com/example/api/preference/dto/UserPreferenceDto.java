package com.example.api.preference.dto;

import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceDto {
    @NotNull
    private Long userId;
    
    @NotNull
    private Long preferenceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    public UserPreferenceEntity toEntity() {
        return UserPreferenceEntity.builder()
                .userId(userId)
                .preferenceId(preferenceId)
                .build();
    }
}