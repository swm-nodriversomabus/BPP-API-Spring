package com.example.api.preference.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComparePreferenceDto {
    @NotNull
    private Long preferenceId;
    
    @NotNull
    private Integer alcoholAmount;

    @NotNull
    private Integer mateAllowedAlcohol;

    @NotNull
    private Integer taste;

    @NotNull
    private Integer allowedMoveTime;

    @NotNull
    private Integer allowedPeople;

    @NotNull
    private Integer preferGender;

    @NotNull
    private Boolean smoke;

    @NotNull
    private Integer preferSmoke;
    
    @NotNull
    private LocalDateTime createdAt;
    
    @NotNull
    private LocalDateTime updatedAt;

    @NotNull
    private Integer slang;
}