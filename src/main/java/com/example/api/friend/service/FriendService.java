package com.example.api.friend.service;

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
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.FindUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendService implements AddFriendUsecase, FindFriendUsecase, DeleteFriendUsecase {
    private final UserMapperInterface userMapper;
    private final FriendMapperInterface friendMapper;
    private final FindUserPort findUserPort;
    private final AddFriendPort addFriendPort;
    private final FindFriendPort findFriendPort;
    private final DeleteFriendPort deleteFriendPort;
    
    @Override
    @Transactional
    public FriendDto addFriend(FriendDto friendDto) {
        Friend friend = addFriendPort.addFriend(friendMapper.toDomain(friendDto));
        return friendMapper.toDto(friend);
    }
    
    @Override
    public List<FindUserDto> getFriendList(String userId) {
        List<FindUserDto> friendList = new ArrayList<>();
        try {
            List<FriendEntity> friendPairList = findFriendPort.getFriendList(UUID.fromString(userId));
            for (FriendEntity friendPair: friendPairList) {
                friendList.add(userMapper.toDto(findUserPort.getByUserId(friendPair.getUserId()).orElseThrow()));
            }
        } catch (IllegalArgumentException e) {
            log.warn("Invalid userId: UUID transform failed.");
        }
        return friendList;
    }
    
    @Override
    @Transactional
    public void deleteFriend(FriendDto friendDto) {
        deleteFriendPort.deleteFriend(friendMapper.toDomain(friendDto));
    }
}