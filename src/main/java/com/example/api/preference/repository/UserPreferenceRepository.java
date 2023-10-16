package com.example.api.preference.repository;

import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import com.example.api.preference.adapter.out.persistence.UserPreferencePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPreferenceRepository extends JpaRepository<UserPreferenceEntity, UserPreferencePK> {
    UserPreferenceEntity getByUserId(UUID userId);
}