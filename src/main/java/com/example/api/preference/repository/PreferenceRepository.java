package com.example.api.preference.repository;

import com.example.api.preference.adapter.out.persistence.PreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<PreferenceEntity, Long> {
    Optional<PreferenceEntity> getPreferenceByPreferenceId(Long preferenceId);
}