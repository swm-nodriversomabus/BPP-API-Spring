package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.FindPreferenceDto;

import java.util.Optional;
import java.util.UUID;

public interface FindPreferenceUsecase {
    Optional<FindPreferenceDto> getByPreferenceId(Long preferenceId);
    Optional<ComparePreferenceDto> getComparePreference(Long preferenceId);
    FindPreferenceDto getUserPreference(UUID userId);
    FindPreferenceDto getMatchingPreference(Long matchingId);
}