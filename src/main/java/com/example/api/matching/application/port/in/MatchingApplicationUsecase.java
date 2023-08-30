package com.example.api.matching.application.port.in;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.dto.MatchingApplicationDto;
import com.example.api.matching.dto.MatchingDto;
import com.example.api.user.dto.UserDto;

import java.util.List;

public interface MatchingApplicationUsecase {
    MatchingApplicationDto createMatchingApplication(MatchingApplicationDto matchingApplicationDto);
    List<MatchingDto> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state);
    List<UserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    MatchingApplicationDto updateMatchingApplication(MatchingApplicationDto matchingApplicationDto);
}