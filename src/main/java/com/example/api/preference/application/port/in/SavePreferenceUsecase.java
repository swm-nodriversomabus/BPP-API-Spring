package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.SavePreferenceDto;

public interface SavePreferenceUsecase {
    SavePreferenceDto createPreference(SavePreferenceDto preferenceDto);
    SavePreferenceDto updatePreference(Long preferenceId, SavePreferenceDto preferenceDto);
}