package com.example.api.friend.application.port.in;

import com.example.api.user.dto.UserDto;

import java.util.List;

public interface FindFriendUsecase {
    List<UserDto> getFriendList(Long userId);
}