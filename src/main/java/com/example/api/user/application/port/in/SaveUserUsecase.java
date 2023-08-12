package com.example.api.user.application.port.in;

import com.example.api.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface SaveUserUsecase {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
}