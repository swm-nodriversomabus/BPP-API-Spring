package com.example.api.preference.service;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.utils.AuthenticationUtils;
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

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferenceService implements SavePreferenceUsecase, FindPreferenceUsecase, ComparePreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final FindPreferencePort findPreferencePort;
    private final ComparePreferencePort comparePreferencePort;
    
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
    public ComparePreferenceDto getUserPreference() {
        try {
            SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
            if (securityUser == null) {
                log.error("PreferenceService::getUserPreference: Authentication is needed.");
                throw new Exception();
            }
            Long preferenceId = comparePreferencePort.getUserPreferenceId(securityUser.getUserId());
            if (preferenceId == 0L) {
                throw new Exception();
            }
            return this.getByPreferenceId(preferenceId).orElseThrow(Exception::new);
        } catch (Exception e) {
            log.warn("PreferenceService::getUserPreference: Preference data was not found. Used default preference settings.");
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
    }
    
    @Override
    public ComparePreferenceDto getMatchingPreference(Long matchingId) {
        Long preferenceId = comparePreferencePort.getMatchingPreferenceId(matchingId);
        return this.getByPreferenceId(preferenceId).orElseThrow();
    }
    
    public Integer getMatchingScore(Long matchingId) {
        ComparePreferenceDto userPreference = this.getUserPreference();
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