package com.example.api.preference.service;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;
import com.example.api.preference.adapter.out.persistence.PreferenceMapperInterface;
import com.example.api.preference.application.port.in.MatchingPreferenceUsecase;
import com.example.api.preference.application.port.out.CreateMatchingPreferencePort;
import com.example.api.preference.dto.MatchingPreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingPreferenceService implements MatchingPreferenceUsecase {
    private PreferenceMapperInterface preferenceMapper;
    private CreateMatchingPreferencePort createMatchingPreferencePort;
    
    @Override
    public MatchingPreferenceDto createMatchingPreference(MatchingPreferenceDto matchingPreferenceDto) {
        MatchingPreferenceEntity matchingPreferenceEntity = createMatchingPreferencePort.createMatchingPreference(preferenceMapper.toEntity(matchingPreferenceDto));
        return preferenceMapper.toDto(matchingPreferenceEntity);
    }
}