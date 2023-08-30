package com.example.api.matching.application.port.out;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.domain.MatchingApplication;

import java.util.List;

public interface MatchingApplicationPort {
    MatchingApplication createMatchingApplication(MatchingApplication matchingApplication);
    List<MatchingApplicationEntity> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
    MatchingApplication updateMatchingApplication(MatchingApplication matchingApplication);
}