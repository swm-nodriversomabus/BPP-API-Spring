package com.example.api.preference.dto;

import jakarta.validation.constraints.Min;
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
    @Min(1L)
    private Long matchingId;
    
    @NotNull
    @Min(1L)
    private Long preferenceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}