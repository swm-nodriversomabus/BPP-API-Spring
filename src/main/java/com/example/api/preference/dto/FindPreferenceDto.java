package com.example.api.preference.dto;

import com.example.api.preference.type.PreferSmokeEnum;
import com.example.api.preference.type.TasteEnum;
import com.example.api.user.type.UserGenderEnum;
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
public class FindPreferenceDto {
    @NotNull
    private Long preferenceId;
    
    @NotNull
    @Min(0)
    private Integer alcoholAmount;

    @NotNull
    @Min(0)
    private Integer mateAllowedAlcohol;

    @NotNull
    private TasteEnum taste;

    @NotNull
    @Min(0)
    private Integer allowedMoveTime;

    @NotNull
    @Min(1)
    private Integer allowedPeople;

    @NotNull
    private UserGenderEnum preferGender;

    @NotNull
    private Boolean smoke;

    @NotNull
    private PreferSmokeEnum preferSmoke;
    
    @NotNull
    private LocalDateTime createdAt;
    
    @NotNull
    private LocalDateTime updatedAt;

    @NotNull
    private Integer slang;
}