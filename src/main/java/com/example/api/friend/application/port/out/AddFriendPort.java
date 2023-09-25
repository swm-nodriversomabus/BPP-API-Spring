package com.example.api.friend.application.port.out;

import com.example.api.friend.domain.Friend;
import org.springframework.stereotype.Component;

@Component
public interface AddFriendPort {
    Friend addFriend(Friend friend);
}