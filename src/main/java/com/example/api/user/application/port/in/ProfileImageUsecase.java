package com.example.api.user.application.port.in;

import java.util.UUID;

public interface ProfileImageUsecase {
    void saveProfileImage(UUID userId, String filename);
    String getProfileImageName(UUID userId);
    void initializeProfileImage(UUID userId);
}