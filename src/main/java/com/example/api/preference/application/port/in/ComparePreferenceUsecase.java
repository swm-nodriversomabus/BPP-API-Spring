package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

public interface ComparePreferenceUsecase {
    ComparePreferenceDto getUserPreference(String userId);
    ComparePreferenceDto getMatchingPreference(Long matchingId);
    Integer getMatchingScore(String userId, Long matchingId);
}