package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.ProfileImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileImageRepository extends JpaRepository<ProfileImageEntity, UUID> {
    Optional<ProfileImageEntity> getByUserId(UUID userId);
}