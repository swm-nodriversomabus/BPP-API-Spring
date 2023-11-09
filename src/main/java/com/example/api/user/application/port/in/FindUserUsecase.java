package com.example.api.user.application.port.in;

import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UserAuthorityCheckDto;

import java.util.List;
import java.util.UUID;

public interface FindUserUsecase {
    List<FindUserDto> getAll();
    FindUserDto getUser(UUID userId);
    UserAuthorityCheckDto getAuthorityUser(UUID userId);
}