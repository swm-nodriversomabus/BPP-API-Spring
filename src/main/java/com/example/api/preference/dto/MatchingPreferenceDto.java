package com.example.api.preference.dto;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchingPreferenceDto {
    @NotNull
    private Long matchingId;
    
    @NotNull
    private Long preferenceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    public MatchingPreferenceEntity toEntity() {
        return MatchingPreferenceEntity.builder()
                .matchingId(matchingId)
                .preferenceId(preferenceId)
                .build();
    }
}