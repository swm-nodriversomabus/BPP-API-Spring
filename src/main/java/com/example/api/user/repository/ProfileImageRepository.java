package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.ProfileImageEntity;
import com.example.api.user.adapter.out.persistence.ProfileImagePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileImageRepository extends JpaRepository<ProfileImageEntity, ProfileImagePK> {
    Optional<ProfileImageEntity> getByUserId(UUID userId);
}