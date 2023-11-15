package com.example.api.preference.service;

import com.example.api.preference.adapter.out.persistence.PreferenceEntity;
import com.example.api.preference.adapter.out.persistence.PreferenceMapperInterface;
import com.example.api.preference.application.port.in.ComparePreferenceUsecase;
import com.example.api.preference.application.port.in.FindPreferenceUsecase;
import com.example.api.preference.application.port.in.SavePreferenceUsecase;
import com.example.api.preference.application.port.out.ComparePreferencePort;
import com.example.api.preference.application.port.out.FindPreferencePort;
import com.example.api.preference.application.port.out.SavePreferencePort;
import com.example.api.preference.domain.Preference;
import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.FindPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import com.example.api.preference.type.PreferSmokeEnum;
import com.example.api.preference.type.TasteEnum;
import com.example.api.user.type.UserGenderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PreferenceService implements SavePreferenceUsecase, FindPreferenceUsecase, ComparePreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final FindPreferencePort findPreferencePort;
    private final ComparePreferencePort comparePreferencePort;
    
    public FindPreferenceDto getDefaultPreference() {
        return FindPreferenceDto.builder()
                .preferenceId(0L)
                .alcoholAmount(2)
                .mateAllowedAlcohol(2)
                .taste(TasteEnum.Spicy)
                .allowedMoveTime(60)
                .allowedPeople(4)
                .preferGender(UserGenderEnum.None)
                .smoke(false)
                .preferSmoke(PreferSmokeEnum.None)
                .slang(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public ComparePreferenceDto getDefaultComparePreference() {
        return ComparePreferenceDto.builder()
                .preferenceId(0L)
                .alcoholAmount(2)
                .mateAllowedAlcohol(2)
                .taste(4)
                .allowedMoveTime(60)
                .allowedPeople(4)
                .preferGender(0)
                .smoke(false)
                .preferSmoke(0)
                .slang(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    
    // CRUD
    
    @Override
    @Transactional
    public FindPreferenceDto createPreference(SavePreferenceDto preferenceDto) {
        Preference preference = savePreferencePort.createPreference(preferenceMapper.toDomain(preferenceDto));
        return preferenceMapper.toDto(preference);
    }
    
    @Override
    public Optional<FindPreferenceDto> getByPreferenceId(Long preferenceId) {
        return findPreferencePort.getByPreferenceId(preferenceId)
                .map(preferenceMapper::toDto);
    }
    
    @Override
    public Optional<ComparePreferenceDto> getComparePreference(Long preferenceId) {
        return findPreferencePort.getByPreferenceId(preferenceId)
                .map(PreferenceEntity::toCompareDto);
    }
    
    @Override
    @Transactional
    public FindPreferenceDto updatePreference(Long preferenceId, SavePreferenceDto preferenceDto) {
        Preference preference = savePreferencePort.updatePreference(preferenceId, preferenceMapper.toDomain(preferenceDto));
        return preferenceMapper.toDto(preference);
    }
    
    // ComparePreference
    
    @Override
    public FindPreferenceDto getUserPreference(UUID userId) {
        Long preferenceId = comparePreferencePort.getUserPreferenceId(userId);
        if (preferenceId == 0L) {
            log.warn("PreferenceService::getUserPreference: User preference data not found");
            return this.getDefaultPreference();
            //throw new CustomException(ErrorCodeEnum.PREFERENCE_NOT_FOUND);
        }
        Optional<FindPreferenceDto> preferenceDto = this.getByPreferenceId(preferenceId);
        if (preferenceDto.isEmpty()) {
            log.warn("PreferenceService::getUserPreference: User preference data not found");
            return this.getDefaultPreference();
            //throw new CustomException(ErrorCodeEnum.PREFERENCE_NOT_FOUND);
        }
        return preferenceDto.get();
    }
    
    @Override
    public FindPreferenceDto getMatchingPreference(Long matchingId) {
        Long preferenceId = comparePreferencePort.getMatchingPreferenceId(matchingId);
        return this.getByPreferenceId(preferenceId).orElse(this.getDefaultPreference());
    }
    
    public Integer getMatchingScore(UUID userId, Long matchingId) {
        Long userPreferenceId = comparePreferencePort.getUserPreferenceId(userId);
        Long matchingPreferenceId = comparePreferencePort.getMatchingPreferenceId(matchingId);
        ComparePreferenceDto userPreference = this.getComparePreference(userPreferenceId).orElse(this.getDefaultComparePreference());
        ComparePreferenceDto matchingPreference = this.getComparePreference(matchingPreferenceId).orElse(this.getDefaultComparePreference());
        Integer score = 0;
        score += Math.abs(userPreference.getAlcoholAmount() - matchingPreference.getAlcoholAmount());
        score += Math.abs(userPreference.getMateAllowedAlcohol() - matchingPreference.getMateAllowedAlcohol());
        score += Math.abs(userPreference.getAllowedMoveTime() - matchingPreference.getAllowedMoveTime());
        score += Math.abs(userPreference.getAllowedPeople() - matchingPreference.getAllowedPeople());
        score += userPreference.getSmoke() == matchingPreference.getSmoke() ? 0 : 1;
        score += Math.abs(userPreference.getSlang() - matchingPreference.getSlang());
        return score;
    }
}