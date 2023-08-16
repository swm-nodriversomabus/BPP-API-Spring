package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

import java.util.Optional;

public interface FindPreferenceUsecase {
    public Optional<ComparePreferenceDto> getPreferenceByPreferenceId(Long preferenceId);
}