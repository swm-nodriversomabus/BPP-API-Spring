package com.example.api.friend.repository;

import com.example.api.friend.adapter.out.persistence.FriendEntity;
import com.example.api.friend.adapter.out.persistence.FriendPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FriendRepository extends JpaRepository<FriendEntity, FriendPK> {
    List<FriendEntity> getByUserIdIs(Long userId);
}