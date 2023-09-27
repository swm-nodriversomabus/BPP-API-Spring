package com.example.api.user.application.port.in;

import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UpdateUserDto;

public interface SaveUserUsecase {
    void createUser(CreateUserDto userDto);
    FindUserDto updateUser(Long userId, UpdateUserDto userDto);
}