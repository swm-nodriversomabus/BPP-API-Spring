package com.example.api.matching.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.dto.FindUserDto;

import java.util.List;

public interface MatchingApplicationUsecase {

    MatchingApplication step1(SaveMatchingApplicationDto matchingApplicationDto);

    ChatRoom step2(MatchingApplication matchingApplication);


    void step3(MatchingApplication matchingApplication, ChatRoom chatRoom);

//    ChatRoom createMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto);
    List<FindMatchingDto> getByUserIdIsAndStateEquals(ApplicationStateEnum state);
    List<FindUserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    String getUserStatus(Long matchingId);
    SaveMatchingApplicationDto updateMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto);
}