package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.MatchingStateEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchingStateDto {
    private Long matchingStateId;
    
    @NotEmpty
    private Long matchingId;
    
    @NotEmpty
    private Long userId;
    
    @NotEmpty
    private Boolean complete;
    
    private LocalDateTime createdAt;
    
    public MatchingStateEntity toEntity() {
        return MatchingStateEntity.builder()
                .matchingId(matchingId)
                .userId(userId)
                .complete(complete)
                .build();
    }
}