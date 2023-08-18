package com.example.api.preference.domain;

import com.example.api.preference.type.PreferSmokeEnum;
import com.example.api.preference.type.TasteEnum;
import com.example.api.user.type.UserGenderEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    private Long preferenceId;
    private Integer alcoholAmount;
    private Integer mateAllowedAlcohol;
    private TasteEnum taste;
    private Integer allowedMoveTime;
    private Integer allowedPeople;
    private UserGenderEnum preferGender;
    private Boolean smoke;
    private PreferSmokeEnum preferSmoke;
    private Integer slang;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}