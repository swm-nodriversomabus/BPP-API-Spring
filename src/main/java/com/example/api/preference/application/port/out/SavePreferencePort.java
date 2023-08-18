package com.example.api.preference.application.port.out;

import com.example.api.preference.domain.Preference;

public interface SavePreferencePort {
    Preference createPreference(Preference preference);
    Preference updatePreference(Long preferenceId, Preference preference);
}