package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

public interface ComparePreferenceUsecase {
    ComparePreferenceDto getUserPreference(Long userId);
    ComparePreferenceDto getMatchingPreference(Long matchingId);
    Integer getMatchingScore(Long userId, Long matchingId);
}