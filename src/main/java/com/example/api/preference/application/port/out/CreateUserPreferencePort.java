package com.example.api.preference.application.port.out;

import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;

public interface CreateUserPreferencePort {
    UserPreferenceEntity createUserPreference(UserPreferenceEntity userPreferenceEntity);
}