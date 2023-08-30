package com.example.api.preference.service;

import com.example.api.preference.adapter.out.persistence.PreferenceMapperInterface;
import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import com.example.api.preference.application.port.in.UserPreferenceUsecase;
import com.example.api.preference.application.port.out.CreateUserPreferencePort;
import com.example.api.preference.dto.UserPreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPreferenceService implements UserPreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final CreateUserPreferencePort createUserPreferencePort;
    
    @Override
    public UserPreferenceDto createUserPreference(UserPreferenceDto userPreferenceDto) {
        UserPreferenceEntity userPreferenceEntity = createUserPreferencePort.createUserPreference(preferenceMapper.toEntity(userPreferenceDto));
        return preferenceMapper.toDto(userPreferenceEntity);
    }
}