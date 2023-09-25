package com.example.api.friend.application.port.out;

import com.example.api.friend.domain.Friend;
import org.springframework.stereotype.Component;

@Component
public interface DeleteFriendPort {
    void deleteFriend(Friend friend);
}