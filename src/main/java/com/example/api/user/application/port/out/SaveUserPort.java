package com.example.api.user.application.port.out;

import com.example.api.user.domain.User;

public interface SaveUserPort {
    User createUser(User user);
    User updateUser(Long userId, User user);
}