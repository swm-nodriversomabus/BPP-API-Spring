package com.example.api.matching.application.port.in;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.dto.FindUserDto;

import java.util.List;
import java.util.UUID;

public interface MatchingApplicationUsecase {
    MatchingApplication createMatchingApplicationData(UUID userId, SaveMatchingApplicationDto matchingApplicationDto);
    List<FindMatchingDto> getByUserIdIsAndStateEquals(UUID userId, ApplicationStateEnum state);
    List<FindUserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    String getUserStatus(UUID userId, Long matchingId);
    void approveMatchingApplication(SaveMatchingApplicationDto matchingApplicationPK);
    void declineMatchingApplication(SaveMatchingApplicationDto matchingApplicationPK);
}