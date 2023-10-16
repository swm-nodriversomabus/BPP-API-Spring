package com.example.api.user.application.port.out;

import com.example.api.user.domain.CreateUser;
import com.example.api.user.domain.User;

import java.util.UUID;

public interface SaveUserPort {
    void createUser(CreateUser user);
    User updateUser(UUID userId, User user);
}