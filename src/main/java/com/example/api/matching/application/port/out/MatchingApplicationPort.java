package com.example.api.matching.application.port.out;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.domain.MatchingApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchingApplicationPort {
    MatchingApplication saveMatchingApplication(MatchingApplication matchingApplication);
    Optional<MatchingApplicationEntity> getByMatchingApplicationPK(MatchingApplicationPK matchingApplicationPK);
    List<MatchingApplicationEntity> getByUserIdIsAndStateEquals(UUID userId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state);
}