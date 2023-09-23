package com.example.api.user.application.port.in;

import com.example.api.user.dto.CreaeUserDto;
import com.example.api.user.dto.UserDto;

public interface SaveUserUsecase {
    void createUser(CreaeUserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
}