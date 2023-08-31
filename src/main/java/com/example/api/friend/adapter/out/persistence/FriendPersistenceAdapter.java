package com.example.api.friend.adapter.out.persistence;

import com.example.api.friend.application.port.out.AddFriendPort;
import com.example.api.friend.application.port.out.DeleteFriendPort;
import com.example.api.friend.application.port.out.FindFriendPort;
import com.example.api.friend.domain.Friend;
import com.example.api.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendPersistenceAdapter implements AddFriendPort, FindFriendPort, DeleteFriendPort {
    private final FriendMapperInterface friendMapper;
    private final FriendRepository friendRepository;
    
    @Override
    public Friend addFriend(Friend friend) {
        FriendEntity friendData = friendRepository.save(friendMapper.toEntity(friend));
        return friendMapper.toDomain(friendData);
    }
    
    @Override
    public List<FriendEntity> getFriendList(Long userId) {
        return friendRepository.getByUserIdIs(userId);
    }
    
    @Override
    public void deleteFriend(Friend friend) {
        friendRepository.delete(friendMapper.toEntity(friend));
    }
}