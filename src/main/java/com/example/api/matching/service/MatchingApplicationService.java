package com.example.api.matching.service;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.fcm.dto.FcmDto;
import com.example.api.fcm.service.FcmService;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.member.service.MemberService;
import com.example.api.notification.adapter.out.persistence.NotificationMapperInterface;
import com.example.api.notification.application.port.out.SaveNotificationPort;
import com.example.api.notification.application.port.out.UserNotificationPort;
import com.example.api.notification.domain.Notification;
import com.example.api.notification.dto.SaveNotificationDto;
import com.example.api.notification.dto.UserNotificationDto;
import com.example.api.notification.type.NotificationTypeEnum;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.FindUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MatchingApplicationService implements MatchingApplicationUsecase {
    private final FindUserPort findUserPort;
    private final FindMatchingPort findMatchingPort;
    private final MatchingApplicationPort matchingApplicationPort;
    private final SaveNotificationPort saveNotificationPort;
    private final UserNotificationPort userNotificationPort;
    private final UserMapperInterface userMapper;
    private final MatchingMapperInterface matchingMapper;
    private final NotificationMapperInterface notificationMapper;
    private final MemberService memberService;
    private final FcmService fcmService;

    /**
     * createMatchingApplication Step 1
     * @param matchingApplicationDto (데이터)
     * @return MatchingApplication
     */
    @Override
    @Transactional
    public MatchingApplication createMatchingApplicationData(UUID userId, SaveMatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingMapper.toDomain(matchingApplicationDto);
        if (matchingApplication.getUserId() == null) {
            matchingApplication.setUserId(userId);
        }
        return matchingApplicationPort.saveMatchingApplication(matchingApplication);
    }

    @Override
    public List<FindMatchingDto> getByUserIdAndStateEquals(UUID userId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByUserIdAndStateEquals(userId, state);
        List<FindMatchingDto> matchingList = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            Optional<MatchingEntity> matchingEntity = findMatchingPort.getByMatchingId(matchingPair.getMatchingId());
            if (matchingEntity.isEmpty()) {
                log.warn("MatchingApplicationService::getByUserIdAndStateEquals: Matching with ID {} doesn't exist", matchingPair.getMatchingId());
            } else {
                Matching matching = matchingMapper.toDomain(matchingEntity.get());
                matching.setCurrentMember(matchingApplicationPort.getByMatchingIdAndStateEquals(matching.getMatchingId(), ApplicationStateEnum.Approved).size() + 1);
                matchingList.add(matchingMapper.toDto(matching));
            }
        }
        return matchingList;
    }

    @Override
    public List<FindUserInfoDto> getByMatchingIdAndStateEquals(Long matchingId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByMatchingIdAndStateEquals(matchingId, state);
        List<FindUserInfoDto> userList = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            Optional<UserEntity> userEntity = findUserPort.getByUserId(matchingPair.getUserId());
            if (userEntity.isEmpty()) {
                log.warn("MatchingApplicationService::getByMatchingIdAndStateEquals: User with ID {} doesn't exist", matchingPair.getUserId());
            } else {
                userList.add(userMapper.toInfoDto(userEntity.get()));
            }
        }
        return userList;
    }
    
    @Override
    public List<FindUserInfoDto> getByMatchingIdAndStateIn(Long matchingId, List<ApplicationStateEnum> stateList) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByMatchingIdAndStateIn(matchingId, stateList);
        List<FindUserInfoDto> userList = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            Optional<UserEntity> userEntity = findUserPort.getByUserId(matchingPair.getUserId());
            if (userEntity.isEmpty()) {
                log.warn("MatchingApplicationService::getByMatchingIdAndStateIn: User with ID {} doesn't exist", matchingPair.getUserId());
            } else {
                userList.add(userMapper.toInfoDto(userEntity.get()));
            }
        }
        return userList;
    }

    @Override
    public String getUserStatus(UUID userId, Long matchingId) {
        MatchingApplicationPK matchingApplicationPK = new MatchingApplicationPK(userId, matchingId);
        Optional<MatchingApplicationEntity> statusData = matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK);
        if (statusData.isPresent()) {
            String status = statusData.get().getState().toString();
            return status.substring(status.indexOf('.') + 1, status.indexOf('('));
        } else {
            return "None";
        }
    }

    @Override
    @Transactional
    public void processMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto) {
        ApplicationStateEnum state = matchingApplicationDto.getState();
        MatchingApplicationPK matchingApplicationPK = MatchingApplicationPK.builder()
                .userId(matchingApplicationDto.getUserId())
                .matchingId(matchingApplicationDto.getMatchingId())
                .build();
        Optional<MatchingApplicationEntity> matchingApplicationEntity = matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK);
        if (matchingApplicationEntity.isEmpty()) {
            log.error("MatchingApplicationService::processMatchingApplication: Data not found");
            throw new CustomException(ErrorCodeEnum.APPLICATION_NOT_FOUND);
        }
        
        MatchingApplication matchingApplication = matchingMapper.toDomain(matchingApplicationEntity.get());
        matchingApplication.setState(state);
        matchingApplicationPort.saveMatchingApplication(matchingApplication);
        
        // 신청 수락 시 매칭 채팅방에 멤버 초대
        if (state.equals(ApplicationStateEnum.Approved)) {
            MatchingEntity matchingEntity = findMatchingPort.getByMatchingId(matchingApplication.getMatchingId()).get();
            memberService.addMember(matchingEntity.getChatRoomId(), matchingApplication.getUserId());
        }
        
        // 알림 추가
        SaveNotificationDto saveNotificationDto = SaveNotificationDto.builder()
                .type(state.equals(ApplicationStateEnum.Approved) ? NotificationTypeEnum.ApplicationApproved : NotificationTypeEnum.ApplicationDeclined)
                .content(state.equals(ApplicationStateEnum.Approved) ? "매칭 신청이 수락되었어요!" : "매칭 신청이 거절되었어요.")
                .isActive(true)
                .build();
        Notification notification = saveNotificationPort.createNotification(notificationMapper.toDomain(saveNotificationDto));
        UserNotificationDto userNotificationDto = UserNotificationDto.builder()
                .userId(matchingApplicationDto.getUserId())
                .notificationId(notification.getNotificationId())
                .isRead(false)
                .readAt(null)
                .build();
        userNotificationPort.createUserNotification(notificationMapper.toEntity(userNotificationDto));
        
        // 푸시 알림 전송
        FcmDto fcmDto = FcmDto.builder()
                .userId(matchingApplication.getUserId())
                .title(state.equals(ApplicationStateEnum.Approved) ? "신청 수락" : "신청 거절")
                .body(state.equals(ApplicationStateEnum.Approved) ? "매칭 신청이 수락되었어요!" : "매칭 신청이 거절되었어요.")
                .build();
        //fcmService.sendNotification(fcmDto);
    }
}