package com.example.api.preference.application.port.out;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;

public interface CreateMatchingPreferencePort {
    MatchingPreferenceEntity createMatchingPreference(MatchingPreferenceEntity matchingPreferenceEntity);
}