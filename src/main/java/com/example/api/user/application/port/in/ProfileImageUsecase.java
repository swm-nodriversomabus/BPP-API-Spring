package com.example.api.user.application.port.in;

import com.example.api.common.type.ApplicationStateEnum;

import java.util.UUID;

public interface ProfileImageUsecase {
    void saveProfileImage(UUID userId, String filename);
    String getProfileImageName(UUID userId);
    void updateProfileImageState(UUID userId, ApplicationStateEnum state);
    void initializeProfileImage(UUID userId);
}