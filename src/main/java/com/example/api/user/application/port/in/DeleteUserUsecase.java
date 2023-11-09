package com.example.api.user.application.port.in;

import java.util.UUID;

public interface DeleteUserUsecase {
    void deleteAll();
    void deleteUser(UUID userId);
}