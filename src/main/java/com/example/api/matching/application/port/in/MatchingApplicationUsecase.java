package com.example.api.matching.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.dto.FindUserDto;

import java.util.List;

public interface MatchingApplicationUsecase {
    ChatRoom createMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto);
    List<FindMatchingDto> getByUserIdIsAndStateEquals(ApplicationStateEnum state);
    List<FindUserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    SaveMatchingApplicationDto updateMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto);
}