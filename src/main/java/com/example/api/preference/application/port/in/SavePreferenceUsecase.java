package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.FindPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;

public interface SavePreferenceUsecase {
    FindPreferenceDto createPreference(SavePreferenceDto preferenceDto);
    FindPreferenceDto updatePreference(Long preferenceId, SavePreferenceDto preferenceDto);
}