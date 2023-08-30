package com.example.api.matching.dto;


import com.example.api.common.type.ApplicationStateEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchingApplicationDto {
    @NotEmpty
    private Long userId;
    
    @NotEmpty
    private Long matchingId;
    
    @NotEmpty
    private ApplicationStateEnum state;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @NotEmpty
    private Boolean isActive;
}