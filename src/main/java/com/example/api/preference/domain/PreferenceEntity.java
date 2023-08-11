package com.example.api.preference.domain;

import com.example.api.preference.type.PreferSmokeEnum;
import com.example.api.preference.type.TasteEnum;
import com.example.api.user.type.UserGenderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    private TasteEnum taste;
    
    @Column(nullable = false)
    private Integer allowedMoveTime;
    
    @Column(nullable = false)
    private Integer allowedPeople;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderEnum preferGender;
    
    @Column(nullable = false)
    private Boolean smoke;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PreferSmokeEnum preferSmoke;
    
    @Column(nullable = false)
    private Integer slang;
}