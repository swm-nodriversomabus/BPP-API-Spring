package com.example.api.preference.application.port.out;

public interface ComparePreferencePort {
    Long getUserPreferenceId(Long userId);
    Long getMatchingPreferenceId(Long matchingId);
}