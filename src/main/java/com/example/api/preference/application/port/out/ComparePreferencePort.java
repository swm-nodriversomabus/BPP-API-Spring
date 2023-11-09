package com.example.api.preference.application.port.out;

import java.util.UUID;

public interface ComparePreferencePort {
    Long getUserPreferenceId(UUID userId);
    Long getMatchingPreferenceId(Long matchingId);
}