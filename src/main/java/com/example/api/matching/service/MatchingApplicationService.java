package com.example.api.matching.service;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.chatroom.service.ChatRoomService;
import com.example.api.chatroom.type.ChatRoomEnum;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.fcm.dto.FcmDto;
import com.example.api.fcm.service.FcmService;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.FindMatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.member.dto.AddMemberDto;
import com.example.api.member.service.MemberService;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.FindUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
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

    /**
     * createMatchingApplication Step 1
     * @param matchingApplicationDto (데이터)
     * @return MatchingApplication
     */
    @Override
    @Transactional
    public MatchingApplication createMatchingApplicationData(SaveMatchingApplicationDto matchingApplicationDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingApplicationService::createMatchingApplicationData: Authentication is needed.");
            return MatchingApplication.builder().build();
        }
        MatchingApplication matchingApplication = matchingMapper.toDomain(matchingApplicationDto);
        if (matchingApplication.getUserId() == null) {
            matchingApplication.setUserId(securityUser.getUserId());
        }
        return matchingApplicationPort.createMatchingApplication(matchingApplication);
    }

    /**
     * createMatchingApplication Step 2
     * @param matchingApplication (데이터)
     * @return ChatRoom
     */
    @Override
    @Transactional
    public ChatRoom createMatchingChatRoom(MatchingApplication matchingApplication) {
        CreateChatRoomDto createChatRoomDto = CreateChatRoomDto.builder()
                .masterId(matchingApplication.getUserId())
                .chatroomName("매칭 신청") // 이거 바꿔야 함
                .type(ChatRoomEnum.Normal)
                .isActive(true)
                .build();
        return chatRoomService.createRoom(createChatRoomDto);
    }

    /**
     * createMatchingApplication Step 3
     * @param matchingApplication (데이터)
     * @param chatRoom (데이터)
     */
    @Override
    @Transactional
    public ChatRoom setupMatchingChatRoom(MatchingApplication matchingApplication, ChatRoom chatRoom) {
        List<UUID> memberIds = new ArrayList<>();
        memberIds.add(matchingApplication.getUserId());
        UUID matchingWriterId = findMatchingPort.getByMatchingId(matchingApplication.getMatchingId())
                .orElseThrow(NoSuchElementException::new)
                .getWriterId();
        memberIds.add(matchingWriterId);

        AddMemberDto addMemberDto = AddMemberDto.builder()
                .chatroomId(chatRoom.getChatroomId())
                .memberIds(memberIds)
                .build();
        memberService.addMember(addMemberDto);
        
        chatRoom.setMembers(memberIds);
        return chatRoom;
    }
    
    @Override
    public List<FindMatchingDto> getByUserIdIsAndStateEquals(ApplicationStateEnum state) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingApplicationService::getByUserIdAndStateEquals: Authentication is needed.");
            return new ArrayList<>();
        }
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByUserIdIsAndStateEquals(securityUser.getUserId(), state);
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
    public String getUserStatus(Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingApplicationService::getUserStatus: Authentication is needed.");
            return "Error";
        }
        MatchingApplicationPK matchingApplicationPK = new MatchingApplicationPK(securityUser.getUserId(), matchingId);
        Optional<MatchingApplicationEntity> statusData = matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK);
        if (statusData.isPresent()) {
            String status = statusData.get().getState().toString();
            return status.substring(status.indexOf('.') + 1, status.indexOf('('));
        } else {
            return "None";
        }
    }
    
    @Transactional
    public FindMatchingApplicationDto updateMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingApplicationPort.updateMatchingApplication(matchingMapper.toDomain(matchingApplicationDto));
        return matchingMapper.toDto(matchingApplication);
    }

    @Override
    @Transactional
    public void approveMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto) {
        MatchingApplicationPK matchingApplicationPK = MatchingApplicationPK.builder()
                .userId(matchingApplicationDto.getUserId())
                .matchingId(matchingApplicationDto.getMatchingId())
                .build();
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

    @Override
    @Transactional
    public void declineMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto) {
        MatchingApplicationPK matchingApplicationPK = MatchingApplicationPK.builder()
                .userId(matchingApplicationDto.getUserId())
                .matchingId(matchingApplicationDto.getMatchingId())
                .build();
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