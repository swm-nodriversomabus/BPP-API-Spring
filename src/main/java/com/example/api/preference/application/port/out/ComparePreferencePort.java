package com.example.api.preference.application.port.out;

import org.springframework.stereotype.Component;

@Component
public interface ComparePreferencePort {
    Long getUserPreferenceId(Long userId);
    Long getMatchingPreferenceId(Long matchingId);
}