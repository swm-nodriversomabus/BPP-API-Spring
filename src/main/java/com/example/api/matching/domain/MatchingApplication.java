package com.example.api.matching.domain;

import com.example.api.common.type.ApplicationStateEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingApplication {
    private Long userId;
    private Long matchingId;
    private ApplicationStateEnum state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}