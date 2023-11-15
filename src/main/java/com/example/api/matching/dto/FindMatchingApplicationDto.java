package com.example.api.matching.dto;

import com.example.api.common.type.ApplicationStateEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindMatchingApplicationDto {
    @NotNull
    private UUID userId;

    @NotNull
    private Long matchingId;

    @NotNull
    private ApplicationStateEnum state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    private Boolean isActive;
}