package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

import java.util.UUID;

public interface ComparePreferenceUsecase {
    ComparePreferenceDto getUserPreference(UUID userId);
    ComparePreferenceDto getMatchingPreference(Long matchingId);
}