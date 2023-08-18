package com.example.api.preference.adapter.out.persistence;

import com.example.api.preference.domain.Preference;
import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreferenceMapper {
    public Preference fromDtoToDomain(SavePreferenceDto preferenceDto) {
        return Preference.builder()
                .alcoholAmount(preferenceDto.getAlcoholAmount())
                .mateAllowedAlcohol(preferenceDto.getMateAllowedAlcohol())
                .taste(preferenceDto.getTaste())
                .allowedMoveTime(preferenceDto.getAllowedMoveTime())
                .allowedPeople(preferenceDto.getAllowedPeople())
                .preferGender(preferenceDto.getPreferGender())
                .smoke(preferenceDto.getSmoke())
                .preferSmoke(preferenceDto.getPreferSmoke())
                .slang(preferenceDto.getSlang())
                .build();
    }
    
    public PreferenceEntity fromDomainToEntity(Preference preference) {
        return PreferenceEntity.builder()
                .preferenceId(preference.getPreferenceId())
                .alcoholAmount(preference.getAlcoholAmount())
                .mateAllowedAlcohol(preference.getMateAllowedAlcohol())
                .taste(preference.getTaste())
                .allowedMoveTime(preference.getAllowedMoveTime())
                .allowedPeople(preference.getAllowedPeople())
                .preferGender(preference.getPreferGender())
                .smoke(preference.getSmoke())
                .preferSmoke(preference.getPreferSmoke())
                .slang(preference.getSlang())
                .build();
    }
    
    public Preference fromEntityToDomain(PreferenceEntity preferenceEntity) {
        return Preference.builder()
                .preferenceId(preferenceEntity.getPreferenceId())
                .alcoholAmount(preferenceEntity.getAlcoholAmount())
                .mateAllowedAlcohol(preferenceEntity.getMateAllowedAlcohol())
                .taste(preferenceEntity.getTaste())
                .allowedMoveTime(preferenceEntity.getAllowedMoveTime())
                .allowedPeople(preferenceEntity.getAllowedPeople())
                .preferGender(preferenceEntity.getPreferGender())
                .smoke(preferenceEntity.getSmoke())
                .preferSmoke(preferenceEntity.getPreferSmoke())
                .slang(preferenceEntity.getSlang())
                .createdAt(preferenceEntity.getCreatedAt())
                .updatedAt(preferenceEntity.getUpdatedAt())
                .build();
    }

    public SavePreferenceDto fromDomainToSaveDto(Preference preference) {
        return SavePreferenceDto.builder()
                .preferenceId(preference.getPreferenceId())
                .alcoholAmount(preference.getAlcoholAmount())
                .mateAllowedAlcohol(preference.getMateAllowedAlcohol())
                .taste(preference.getTaste())
                .allowedMoveTime(preference.getAllowedMoveTime())
                .allowedPeople(preference.getAllowedPeople())
                .preferGender(preference.getPreferGender())
                .smoke(preference.getSmoke())
                .preferSmoke(preference.getPreferSmoke())
                .slang(preference.getSlang())
                .createdAt(preference.getCreatedAt())
                .updatedAt(preference.getUpdatedAt())
                .build();
    }
    
    public ComparePreferenceDto fromDomainToCompareDto(Preference preference) {
        return ComparePreferenceDto.builder()
                .alcoholAmount(preference.getAlcoholAmount())
                .mateAllowedAlcohol(preference.getMateAllowedAlcohol())
                .taste(preference.getTaste().getTasteCode())
                .allowedMoveTime(preference.getAllowedMoveTime())
                .allowedPeople(preference.getAllowedPeople())
                .preferGender(preference.getPreferGender().getGenderCode())
                .smoke(preference.getSmoke())
                .preferSmoke(preference.getPreferSmoke().getSmokeCode())
                .slang(preference.getSlang())
                .build();
    }
}