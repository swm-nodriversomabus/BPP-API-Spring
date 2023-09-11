package com.example.api.preference.repository;

import com.example.api.preference.adapter.out.persistence.UserPreferenceEntity;
import com.example.api.preference.adapter.out.persistence.UserPreferencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserPreferenceRepository extends JpaRepository<UserPreferenceEntity, UserPreferencePK> {
    @Query("SELECT U FROM UserPreferenceEntity U WHERE U.userId = :userId")
    public UserPreferenceEntity getUserPreferenceId(@Param("userId") Long userId);
}