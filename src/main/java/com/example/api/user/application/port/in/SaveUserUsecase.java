package com.example.api.user.application.port.in;

import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UpdateUserDto;

import java.util.UUID;

public interface SaveUserUsecase {
    void createUser(CreateUserDto userDto);
    FindUserDto updateUser(UUID userId, UpdateUserDto userDto);
}