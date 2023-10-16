package com.example.api.matching.service;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.chatroom.service.ChatRoomService;
import com.example.api.chatroom.type.ChatRoomEnum;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.fcm.dto.FcmDto;
import com.example.api.fcm.service.FcmService;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.MatchingApplicationDto;
import com.example.api.member.dto.AddMemberDto;
import com.example.api.member.service.MemberService;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.FindUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingApplicationService implements MatchingApplicationUsecase {
    private final UserMapperInterface userMapper;
    private final MatchingMapperInterface matchingMapper;
    private final FindUserPort findUserPort;
    private final FindMatchingPort findMatchingPort;
    private final MatchingApplicationPort matchingApplicationPort;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final FcmService fcmService;
    
    @Override
    @Transactional
    public ChatRoom createMatchingApplication(MatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingApplicationPort.createMatchingApplication(matchingMapper.toDomain(matchingApplicationDto));
        
        CreateChatRoomDto createChatRoomDto = CreateChatRoomDto.builder()
                .masterId(matchingApplicationDto.getUserId())
                .chatroomName("매칭 신청") // 이거 바꿔야 함
                .type(ChatRoomEnum.Normal)
                .isActive(true)
                .build();
        ChatRoom chatRoom = chatRoomService.createRoom(createChatRoomDto);
        
        List<UUID> memberIds = new ArrayList<>();
        memberIds.add(matchingApplicationDto.getUserId());
        UUID matchingWriterId = findMatchingPort.getByMatchingId(matchingApplicationDto.getMatchingId())
                .orElseThrow(NoSuchElementException::new)
                .getWriterId();
        memberIds.add(matchingWriterId);

        AddMemberDto addMemberDto = AddMemberDto.builder()
                .chatroomId(chatRoom.getChatroomId())
                .memberIds(memberIds)
                .build();
        memberService.addMember(addMemberDto);
        
        return chatRoom;
    }
    
    @Override
    public List<FindMatchingDto> getByUserIdIsAndStateEquals(String userId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByUserIdIsAndStateEquals(UUID.fromString(userId), state);
        List<FindMatchingDto> matchingData = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            matchingData.add(matchingMapper.toDto(findMatchingPort.getByMatchingId(matchingPair.getMatchingId()).orElseThrow()));
        }
        return matchingData;
    }

    @Override
    public List<FindUserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByMatchingIdIsAndStateEquals(matchingId, state);
        List<FindUserDto> userData = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            userData.add(userMapper.toDto(findUserPort.getByUserId(matchingPair.getUserId()).orElseThrow()));
        }
        return userData;
    }
    
    @Override
    @Transactional
    public MatchingApplicationDto updateMatchingApplication(MatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingApplicationPort.updateMatchingApplication(matchingMapper.toDomain(matchingApplicationDto));
        return matchingMapper.toDto(matchingApplication);
    }

    @Transactional
    public void acceptMatchingApplication(MatchingApplicationPK matchingApplicationPK) {
        MatchingApplicationEntity matchingApplicationEntity = matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK)
                .orElseThrow(NoSuchElementException::new);
        MatchingApplication matchingApplication = matchingMapper.toDomain(matchingApplicationEntity);
        matchingApplication.setState(ApplicationStateEnum.Approved);
        FcmDto fcmDto = FcmDto.builder()
                .userId(matchingApplication.getUserId())
                .title("신청 수락")
                .body("매칭 신청이 수락되었어요!")
                .build();
        fcmService.sendNotification(fcmDto);
    }

    @Transactional
    public void declineMatchingApplication(MatchingApplicationPK matchingApplicationPK) {
        MatchingApplicationEntity matchingApplicationEntity = matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK)
                .orElseThrow(NoSuchElementException::new);
        MatchingApplication matchingApplication = matchingMapper.toDomain(matchingApplicationEntity);
        matchingApplication.setState(ApplicationStateEnum.Declined);
        FcmDto fcmDto = FcmDto.builder()
                .userId(matchingApplication.getUserId())
                .title("신청 거절")
                .body("매칭 신청이 거절되었어요.")
                .build();
        fcmService.sendNotification(fcmDto);
    }
}