package com.example.api.friend.application.port.out;

import com.example.api.friend.adapter.out.persistence.FriendEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FindFriendPort {
    List<FriendEntity> getFriendList(Long userId);
}