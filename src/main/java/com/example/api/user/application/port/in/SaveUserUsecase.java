package com.example.api.user.application.port.in;

import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UserDto;

public interface SaveUserUsecase {
    void createUser(CreateUserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
}