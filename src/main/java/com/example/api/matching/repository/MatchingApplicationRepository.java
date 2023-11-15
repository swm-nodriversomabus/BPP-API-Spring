package com.example.api.matching.repository;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MatchingApplicationRepository extends JpaRepository<MatchingApplicationEntity, MatchingApplicationPK> {
    Optional<MatchingApplicationEntity> getByUserIdAndMatchingId(UUID userId, Long matchingId);
    List<MatchingApplicationEntity> getByUserIdAndStateEquals(UUID userId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdAndStateEquals(Long matchingId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdAndStateIn(Long matchingId, List<ApplicationStateEnum> stateList);
}