package com.example.api.preference.adapter.out.persistence;

import com.example.api.preference.application.port.out.*;
import com.example.api.preference.domain.Preference;
import com.example.api.preference.repository.MatchingPreferenceRepository;
import com.example.api.preference.repository.PreferenceRepository;
import com.example.api.preference.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PreferencePersistenceAdapter implements
        SavePreferencePort, FindPreferencePort, ComparePreferencePort, CreateUserPreferencePort, CreateMatchingPreferencePort {
    private final PreferenceMapperInterface preferenceMapper;
    private final PreferenceRepository preferenceRepository;
    private final UserPreferenceRepository userPreferenceRepository;
    private final MatchingPreferenceRepository matchingPreferenceRepository;
    
    // Preference
    
    @Override
    public Preference createPreference(Preference preference) {
        PreferenceEntity preferenceData = preferenceRepository.save(preferenceMapper.toEntity(preference));
        return preferenceMapper.toDomain(preferenceData);
    }
    
    @Override
    public Optional<PreferenceEntity> getPreferenceByPreferenceId(Long preferenceId) {
        return preferenceRepository.getPreferenceByPreferenceId(preferenceId);
    }
    
    @Override
    public Preference updatePreference(Long preferenceId, Preference preference) {
        preference.setPreferenceId(preferenceId);
        PreferenceEntity preferenceData = preferenceRepository.save(preferenceMapper.toEntity(preference));
        return preferenceMapper.toDomain(preferenceData);
    }
    
    // UserPreference
    
    @Override
    public UserPreferenceEntity createUserPreference(UserPreferenceEntity userPreferenceEntity) {
        return userPreferenceRepository.save(userPreferenceEntity);
    }
    
    @Override
    public Long getUserPreferenceId(Long userId) {
        UserPreferenceEntity userPreferenceData = userPreferenceRepository.getUserPreferenceId(userId);
        return userPreferenceData.getPreferenceId();
    }
    
    // MatchingPreference
    
    @Override
    public MatchingPreferenceEntity createMatchingPreference(MatchingPreferenceEntity matchingPreferenceEntity) {
        return matchingPreferenceRepository.save(matchingPreferenceEntity);
    }
    
    @Override
    public  Long getMatchingPreferenceId(Long matchingId) {
        MatchingPreferenceEntity matchingPreferenceData = matchingPreferenceRepository.getMatchingPreferenceId(matchingId);
        return matchingPreferenceData.getPreferenceId();
    }
}