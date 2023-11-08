package com.example.api.preference.dto;

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
public class UserPreferenceDto {
    @NotNull
    private UUID userId;
    
    @NotNull
    private Long preferenceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}