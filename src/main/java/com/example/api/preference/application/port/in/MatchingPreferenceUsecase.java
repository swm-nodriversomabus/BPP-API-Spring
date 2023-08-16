package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.MatchingPreferenceDto;

public interface MatchingPreferenceUsecase {
    MatchingPreferenceDto createMatchingPreference(MatchingPreferenceDto matchingPreferenceDto);
}