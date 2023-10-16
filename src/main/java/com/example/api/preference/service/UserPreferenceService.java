package com.example.api.preference.service;

import com.example.api.preference.adapter.out.persistence.PreferenceMapperInterface;
import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import com.example.api.preference.application.port.in.UserPreferenceUsecase;
import com.example.api.preference.application.port.out.ComparePreferencePort;
import com.example.api.preference.application.port.out.CreateUserPreferencePort;
import com.example.api.preference.application.port.out.SavePreferencePort;
import com.example.api.preference.dto.FindPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import com.example.api.preference.dto.UserPreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPreferenceService implements UserPreferenceUsecase {
    private final PreferenceMapperInterface preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final ComparePreferencePort comparePreferencePort;
    private final CreateUserPreferencePort createUserPreferencePort;
    
    @Override
    public UserPreferenceDto createUserPreference(UserPreferenceDto userPreferenceDto) {
        UserPreferenceEntity userPreferenceEntity = createUserPreferencePort.createUserPreference(preferenceMapper.toEntity(userPreferenceDto));
        return preferenceMapper.toDto(userPreferenceEntity);
    }
    
    @Override
    public FindPreferenceDto updateUserPreference(String userId, SavePreferenceDto savePreferenceDto) {
        Long preferenceId = comparePreferencePort.getUserPreferenceId(UUID.fromString(userId));
        return preferenceMapper.toDto(savePreferencePort.updatePreference(preferenceId, preferenceMapper.toDomain(savePreferenceDto)));
    }
}