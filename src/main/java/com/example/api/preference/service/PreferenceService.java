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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferenceService implements SavePreferenceUsecase, FindPreferenceUsecase, ComparePreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final FindPreferencePort findPreferencePort;
    private final ComparePreferencePort comparePreferencePort;
    
    public ComparePreferenceDto getDefaultPreference() {
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
    public Optional<ComparePreferenceDto> getByPreferenceId(Long preferenceId) {
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
    public ComparePreferenceDto getUserPreference(UUID userId) {
        Long preferenceId = comparePreferencePort.getUserPreferenceId(userId);
        if (preferenceId == 0L) {
            log.warn("PreferenceService::getUserPreference: User preference data not found");
            return this.getDefaultPreference();
            //throw new CustomException(ErrorCodeEnum.PREFERENCE_NOT_FOUND);
        }
        Optional<ComparePreferenceDto> preferenceDto = this.getByPreferenceId(preferenceId);
        if (preferenceDto.isEmpty()) {
            log.warn("PreferenceService::getUserPreference: User preference data not found");
            return this.getDefaultPreference();
            //throw new CustomException(ErrorCodeEnum.PREFERENCE_NOT_FOUND);
        }
        return preferenceDto.get();
    }
    
    @Override
    public ComparePreferenceDto getMatchingPreference(Long matchingId) {
        Long preferenceId = comparePreferencePort.getMatchingPreferenceId(matchingId);
        return this.getByPreferenceId(preferenceId).orElse(this.getDefaultPreference());
    }
    
    public Integer getMatchingScore(UUID userId, Long matchingId) {
        ComparePreferenceDto userPreference = this.getUserPreference(userId);
        ComparePreferenceDto matchingPreference = this.getMatchingPreference(matchingId);
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