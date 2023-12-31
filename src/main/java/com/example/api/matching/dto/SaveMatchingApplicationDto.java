package com.example.api.matching.dto;

import com.example.api.common.type.ApplicationStateEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveMatchingApplicationDto {
    private UUID userId;
    
    @NotNull
    @Min(1L)
    private Long matchingId;

    @NotNull
    private ApplicationStateEnum state;

    @NotNull
    private Boolean isActive;
}