package com.example.api.matching.application.port.in;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.dto.MatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.dto.FindUserDto;

import java.util.List;

public interface MatchingApplicationUsecase {
    MatchingApplicationDto createMatchingApplication(MatchingApplicationDto matchingApplicationDto);
    List<FindMatchingDto> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state);
    List<FindUserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    MatchingApplicationDto updateMatchingApplication(MatchingApplicationDto matchingApplicationDto);
}