package com.example.api.user.application.port.in;

import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UserAuthorityCheckDto;

import java.util.List;

public interface FindUserUsecase {
    List<FindUserDto> getAll();
    FindUserDto getUser();
    UserAuthorityCheckDto getAuthorityUser();
}