package com.example.api.preference.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.preference.dto.ComparePreferenceDto;
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
public class PreferenceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;
    
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
    
    public ComparePreferenceDto toCompareDto() {
        return ComparePreferenceDto.builder()
                .alcoholAmount(alcoholAmount)
                .mateAllowedAlcohol(mateAllowedAlcohol)
                .taste(taste.getTasteCode())
                .allowedMoveTime(allowedMoveTime)
                .allowedPeople(allowedPeople)
                .preferGender(preferGender.getGenderCode())
                .smoke(smoke)
                .preferSmoke(preferSmoke.getSmokeCode())
                .slang(slang)
                .build();
    }
}