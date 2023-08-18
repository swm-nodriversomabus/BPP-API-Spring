package com.example.api.user.application.port.in;

import com.example.api.user.dto.UserDto;

public interface SaveUserUsecase {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
}