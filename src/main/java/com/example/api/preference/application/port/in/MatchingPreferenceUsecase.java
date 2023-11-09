package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.FindPreferenceDto;
import com.example.api.preference.dto.MatchingPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;

public interface MatchingPreferenceUsecase {
    MatchingPreferenceDto createMatchingPreference(MatchingPreferenceDto matchingPreferenceDto);
    FindPreferenceDto updateMatchingPreference(Long matchingId, SavePreferenceDto savePreferenceDto);
}