package com.example.api.friend.repository;

import com.example.api.friend.adapter.out.persistence.FriendEntity;
import com.example.api.friend.adapter.out.persistence.FriendPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendPK> {
    List<FriendEntity> getByUserId(UUID userId);

    Optional<FriendEntity> getByUserIdAndFriendId(UUID userId, UUID friendId);
}