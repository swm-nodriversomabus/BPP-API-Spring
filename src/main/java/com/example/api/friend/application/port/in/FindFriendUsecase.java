package com.example.api.friend.application.port.in;

import com.example.api.user.dto.FindUserDto;

import java.util.List;

public interface FindFriendUsecase {
    List<FindUserDto> getFriendList();
}