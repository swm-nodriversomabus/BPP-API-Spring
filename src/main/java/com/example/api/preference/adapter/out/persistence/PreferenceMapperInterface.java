package com.example.api.preference.adapter.out.persistence;

import com.example.api.preference.domain.Preference;
import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.MatchingPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import com.example.api.preference.dto.UserPreferenceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceMapperInterface {
    Preference toDomain(SavePreferenceDto preferenceDto);
    PreferenceEntity toEntity(Preference preference);
    UserPreferenceEntity toEntity(UserPreferenceDto userPreferenceDto);
    MatchingPreferenceEntity toEntity(MatchingPreferenceDto matchingPreferenceDto);
    Preference toDomain(PreferenceEntity preferenceEntity);
    SavePreferenceDto toDto(Preference preference);
    //ComparePreferenceDto toCompareDto(PreferenceEntity preferenceEntity);
    UserPreferenceDto toDto(UserPreferenceEntity userPreferenceEntity);
    MatchingPreferenceDto toDto(MatchingPreferenceEntity matchingPreferenceEntity);
}