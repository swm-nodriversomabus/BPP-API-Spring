package com.example.api.preference.application.port;

import com.example.api.preference.adapter.out.persistence.PreferenceEntity;
import com.example.api.preference.adapter.out.persistence.PreferenceMapper;
import com.example.api.preference.application.port.in.FindPreferenceUsecase;
import com.example.api.preference.application.port.in.SavePreferenceUsecase;
import com.example.api.preference.application.port.out.FindPreferencePort;
import com.example.api.preference.application.port.out.SavePreferencePort;
import com.example.api.preference.domain.Preference;
import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferenceService implements SavePreferenceUsecase, FindPreferenceUsecase {
    private final PreferenceMapper preferenceMapper;
    private final SavePreferencePort savePreferencePort;
    private final FindPreferencePort findPreferencePort;
    
    @Override
    public SavePreferenceDto createPreference(SavePreferenceDto preferenceDto) {
        Preference preference = savePreferencePort.createPreference(preferenceMapper.fromDtoToDomain(preferenceDto));
        return preferenceMapper.fromDomainToSaveDto(preference);
    }
    
    @Override
    public Optional<ComparePreferenceDto> getPreferenceByPreferenceId(Long preferenceId) {
        return findPreferencePort.getPreferenceByPreferenceId(preferenceId)
                .map(PreferenceEntity::toCompareDto);
    }
    
    @Override
    public SavePreferenceDto updatePreference(Long preferenceId, SavePreferenceDto preferenceDto) {
        Preference preference = savePreferencePort.updatePreference(preferenceId, preferenceMapper.fromDtoToDomain(preferenceDto));
        return preferenceMapper.fromDomainToSaveDto(preference);
    }
}