package com.example.api.friend.application.port.out;

import com.example.api.friend.adapter.out.persistence.FriendEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface FindFriendPort {
    List<FriendEntity> getFriendList(UUID userId);

    Optional<FriendEntity> findFriend(UUID userId, UUID friendId);
}