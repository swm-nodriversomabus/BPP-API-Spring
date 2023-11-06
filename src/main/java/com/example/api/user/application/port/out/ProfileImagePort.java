package com.example.api.user.application.port.out;

import com.example.api.user.adapter.out.persistence.ProfileImageEntity;
import com.example.api.user.domain.ProfileImage;

import java.util.Optional;
import java.util.UUID;

public interface ProfileImagePort {
    void saveProfileImage(ProfileImage profileImage);
    Optional<ProfileImageEntity> getProfileImage(UUID userId);
}