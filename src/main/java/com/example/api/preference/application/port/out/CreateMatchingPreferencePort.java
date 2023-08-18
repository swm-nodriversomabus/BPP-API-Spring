package com.example.api.preference.application.port.out;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;
import org.springframework.stereotype.Component;

@Component
public interface CreateMatchingPreferencePort {
    MatchingPreferenceEntity createMatchingPreference(MatchingPreferenceEntity matchingPreferenceEntity);
}