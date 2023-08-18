package com.example.api.preference.dto;

import com.example.api.preference.type.PreferSmokeEnum;
import com.example.api.preference.type.TasteEnum;
import com.example.api.user.type.UserGenderEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SavePreferenceDto {
    private Long preferenceId;
    
    @NotNull
    private Integer alcoholAmount;
    
    @NotNull
    private Integer mateAllowedAlcohol;
    
    @NotNull
    private TasteEnum taste;
    
    @NotNull
    private Integer allowedMoveTime;
    
    @NotNull
    private Integer allowedPeople;
    
    @NotNull
    private UserGenderEnum preferGender;
    
    @NotNull
    private Boolean smoke;
    
    @NotNull
    private PreferSmokeEnum preferSmoke;
    
    @NotNull
    private Integer slang;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}