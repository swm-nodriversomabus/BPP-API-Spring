package com.example.api.user.application.port.in;

import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;

public interface SaveUserUsecase {
    FindUserDto createUser(SaveUserDto userDto);
    FindUserDto updateUser(Long userId, SaveUserDto userDto);
}