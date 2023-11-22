package com.example.api.friend.service;

import com.example.api.blocklist.application.port.out.GetBlockListPort;
import com.example.api.friend.adapter.out.persistence.FriendEntity;
import com.example.api.friend.adapter.out.persistence.FriendMapperInterface;
import com.example.api.friend.application.port.in.AddFriendUsecase;
import com.example.api.friend.application.port.in.DeleteFriendUsecase;
import com.example.api.friend.application.port.in.FindFriendUsecase;
import com.example.api.friend.application.port.out.AddFriendPort;
import com.example.api.friend.application.port.out.DeleteFriendPort;
import com.example.api.friend.application.port.out.FindFriendPort;
import com.example.api.friend.domain.Friend;
import com.example.api.friend.dto.FriendDto;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.FindUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FriendService implements AddFriendUsecase, FindFriendUsecase, DeleteFriendUsecase {
    private final FindUserPort findUserPort;
    private final AddFriendPort addFriendPort;
    private final FindFriendPort findFriendPort;
    private final DeleteFriendPort deleteFriendPort;
    private final GetBlockListPort getBlockListPort;
    private final UserMapperInterface userMapper;
    private final FriendMapperInterface friendMapper;
    
    @Override
    @Transactional
    public FriendDto addFriend(FriendDto friendDto) {
        Friend friend = addFriendPort.addFriend(friendMapper.toDomain(friendDto));
        return friendMapper.toDto(friend);
    }

    @Override
    public Boolean findFriend(UUID userId, UUID friendId) {
        Optional<FriendEntity> friend = findFriendPort.findFriend(userId, friendId);
        return friend.isPresent();
    }

    @Override
    public List<FindUserDto> getFriendList(UUID userId) {
        List<FindUserDto> friendList = new ArrayList<>();
        List<FriendEntity> friendPairList = findFriendPort.getFriendList(userId);
        for (FriendEntity friendPair: friendPairList) {
            Optional<UserEntity> userEntity = findUserPort.getByUserId(friendPair.getFriendId());
            if (userEntity.isPresent() && getBlockListPort.getBlockUser(userId, userEntity.get().getUserId()).isEmpty()) {
                friendList.add(userMapper.toDto(userEntity.get()));
            }
        }
        return friendList;
    }
    
    @Override
    @Transactional
    public void deleteFriend(FriendDto friendDto) {
        deleteFriendPort.deleteFriend(friendMapper.toDomain(friendDto));
    }
}