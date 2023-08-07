package com.example.api.user.application.port.in;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserUsecase {
    void createUser(UserDto userDto);
    List<UserDto> getAll();
    Optional<UserDto> getUserById(Long userId);
    void updateUser(Long userId, UserDto userDto);
    void deleteAll();
    void deleteUser(Long userId);
}