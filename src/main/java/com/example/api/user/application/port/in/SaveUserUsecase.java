package com.example.api.user.application.port.in;

import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UserDto;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;

public interface SaveUserUsecase {
    void createUser(CreateUserDto userDto);
    FindUserDto updateUser(Long userId, SaveUserDto userDto);
}