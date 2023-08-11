package com.example.api.user.application.port.in;

import com.example.api.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserUsecase {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAll();
    Optional<UserDto> getUserById(Long userId);
    UserDto updateUser(UserDto userDto);
    void deleteAll();
    void deleteUser(Long userId);
}