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
public class ComparePreferenceDto {
    @NotNull
    private Long preferenceId;
    
    @NotNull
    @Min(0)
    private Integer alcoholAmount;

    @NotNull
    @Min(0)
    private Integer mateAllowedAlcohol;

    @NotNull
    private Integer taste;

    @NotNull
    @Min(0)
    private Integer allowedMoveTime;

    @NotNull
    @Min(1)
    private Integer allowedPeople;

    @NotNull
    private Integer preferGender;

    @NotNull
    private Boolean smoke;

    @NotNull
    private Integer preferSmoke;

    @NotNull
    private Integer slang;
    
    @NotNull
    private LocalDateTime createdAt;
    
    @NotNull
    private LocalDateTime updatedAt;
}