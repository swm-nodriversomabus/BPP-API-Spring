package com.example.api.preference.adapter.out.persistence;

import com.example.api.preference.type.PreferSmokeType;
import com.example.api.preference.type.TasteType;
import com.example.api.user.type.UserGenderType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="preference")
public class PreferenceEntity {
    @Id
    private Long userId;
    
    @Column(nullable = false)
    private Integer alcoholAmount;
    
    @Column(nullable = false)
    private Integer mateAllowedAlcohol;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TasteType taste;
    
    @Column(nullable = false)
    private Integer allowedMoveTime;
    
    @Column(nullable = false)
    private Integer allowedPeople;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderType preferGender;
    
    @Column(nullable = false)
    private Boolean smoke;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PreferSmokeType preferSmoke;
    
    @Column(nullable = false)
    private Integer slang;
}