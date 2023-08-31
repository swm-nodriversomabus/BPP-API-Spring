package com.example.api.friend.application.port.in;

import com.example.api.friend.dto.FriendDto;

public interface AddFriendUsecase {
    FriendDto addFriend(FriendDto friendDto);
}