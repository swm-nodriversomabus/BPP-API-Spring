package com.example.api.preference.application.port.out;

import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import org.springframework.stereotype.Component;

@Component
public interface CreateUserPreferencePort {
    UserPreferenceEntity createUserPreference(UserPreferenceEntity userPreferenceEntity);
}