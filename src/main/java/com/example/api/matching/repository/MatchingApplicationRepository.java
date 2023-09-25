package com.example.api.matching.repository;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MatchingApplicationRepository extends JpaRepository<MatchingApplicationEntity, MatchingApplicationPK> {
    Optional<MatchingApplicationEntity> getByUserIdAndMatchingId(Long userId, Long matchingId);
    List<MatchingApplicationEntity> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdAndStateEquals(Long matchingId, ApplicationStateEnum state);
}