package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.FindPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import com.example.api.preference.dto.UserPreferenceDto;

import java.util.UUID;

public interface UserPreferenceUsecase {
    UserPreferenceDto createUserPreference(UserPreferenceDto userPreferenceDto);
    FindPreferenceDto updateUserPreference(UUID userId, SavePreferenceDto savePreferenceDto);
}