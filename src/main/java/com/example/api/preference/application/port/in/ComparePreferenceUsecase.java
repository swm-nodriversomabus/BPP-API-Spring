package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

public interface ComparePreferenceUsecase {
    ComparePreferenceDto getUserPreference();
    ComparePreferenceDto getMatchingPreference(Long matchingId);
}