package com.example.api.preference.adapter.out.persistence;

import com.example.api.preference.application.port.out.CreateMatchingPreferencePort;
import com.example.api.preference.application.port.out.CreateUserPreferencePort;
import com.example.api.preference.application.port.out.FindPreferencePort;
import com.example.api.preference.application.port.out.SavePreferencePort;
import com.example.api.preference.domain.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PreferencePersistenceAdapter implements
        SavePreferencePort, FindPreferencePort, CreateUserPreferencePort, CreateMatchingPreferencePort {
    private final PreferenceMapper preferenceMapper;
    private final PreferenceRepository preferenceRepository;
    private final UserPreferenceRepository userPreferenceRepository;
    private final MatchingPreferenceRepository matchingPreferenceRepository;
    
    // Preference
    
    @Override
    public Preference createPreference(Preference preference) {
        PreferenceEntity preferenceData = preferenceRepository.save(preferenceMapper.fromDomainToEntity(preference));
        return preferenceMapper.fromEntityToDomain(preferenceData);
    }
    
    @Override
    public Optional<PreferenceEntity> getPreferenceByPreferenceId(Long preferenceId) {
        return preferenceRepository.getPreferenceByPreferenceId(preferenceId);
    }
    
    @Override
    public Preference updatePreference(Long preferenceId, Preference preference) {
        preference.setPreferenceId(preferenceId);
        PreferenceEntity preferenceData = preferenceRepository.save(preferenceMapper.fromDomainToEntity(preference));
        return preferenceMapper.fromEntityToDomain(preferenceData);
    }
    
    // UserPreference
    
    @Override
    public UserPreferenceEntity createUserPreference(UserPreferenceEntity userPreferenceEntity) {
        return userPreferenceRepository.save(userPreferenceEntity);
    }
    
    // MatchingPreference
    
    @Override
    public MatchingPreferenceEntity createMatchingPreference(MatchingPreferenceEntity matchingPreferenceEntity) {
        return matchingPreferenceRepository.save(matchingPreferenceEntity);
    }
}