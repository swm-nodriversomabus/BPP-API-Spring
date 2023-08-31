package com.example.api.preference.service;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;
import com.example.api.preference.adapter.out.persistence.PreferenceMapperInterface;
import com.example.api.preference.application.port.in.MatchingPreferenceUsecase;
import com.example.api.preference.application.port.out.ComparePreferencePort;
import com.example.api.preference.application.port.out.CreateMatchingPreferencePort;
import com.example.api.preference.application.port.out.SavePreferencePort;
import com.example.api.preference.dto.MatchingPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingPreferenceService implements MatchingPreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final ComparePreferencePort comparePreferencePort;
    private final CreateMatchingPreferencePort createMatchingPreferencePort;
    
    @Override
    public MatchingPreferenceDto createMatchingPreference(MatchingPreferenceDto matchingPreferenceDto) {
        MatchingPreferenceEntity matchingPreferenceEntity = createMatchingPreferencePort.createMatchingPreference(preferenceMapper.toEntity(matchingPreferenceDto));
        return preferenceMapper.toDto(matchingPreferenceEntity);
    }
    
    @Override
    public SavePreferenceDto updateMatchingPreference(Long matchingId, SavePreferenceDto savePreferenceDto) {
        Long preferenceId = comparePreferencePort.getMatchingPreferenceId(matchingId);
        return preferenceMapper.toDto(savePreferencePort.updatePreference(preferenceId, preferenceMapper.toDomain(savePreferenceDto)));
    }
}