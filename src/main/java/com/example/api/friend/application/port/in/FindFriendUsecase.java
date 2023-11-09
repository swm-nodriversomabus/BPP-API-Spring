package com.example.api.friend.application.port.in;

import com.example.api.user.dto.FindUserDto;

import java.util.List;
import java.util.UUID;

public interface FindFriendUsecase {
    List<FindUserDto> getFriendList(UUID userId);
}