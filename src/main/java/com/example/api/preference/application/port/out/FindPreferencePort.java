package com.example.api.preference.application.port.out;

import com.example.api.preference.adapter.out.persistence.PreferenceEntity;

import java.util.Optional;

public interface FindPreferencePort {
    Optional<PreferenceEntity> getByPreferenceId(Long preferenceId);
}