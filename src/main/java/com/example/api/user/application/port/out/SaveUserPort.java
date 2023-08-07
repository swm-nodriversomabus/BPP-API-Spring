package com.example.api.user.application.port.out;

import com.example.api.user.dto.UserDto;

public interface SaveUserPort {
    void createUser(UserDto user);
}