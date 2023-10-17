package com.example.api.preference.application.port.in;

import com.example.api.preference.dto.ComparePreferenceDto;

import java.util.Optional;

public interface FindPreferenceUsecase {
    Optional<ComparePreferenceDto> getByPreferenceId(Long preferenceId);
}