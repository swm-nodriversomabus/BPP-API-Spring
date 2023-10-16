package com.example.api.user.application.port.in;

import com.example.api.user.dto.FindUserDto;

import java.util.List;
import java.util.Optional;

public interface FindUserUsecase {
    List<FindUserDto> getAll();
    Optional<FindUserDto> getUserById(String userId);
}