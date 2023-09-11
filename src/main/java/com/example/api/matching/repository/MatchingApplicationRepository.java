package com.example.api.matching.repository;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingApplicationRepository extends JpaRepository<MatchingApplicationEntity, MatchingApplicationPK> {
    List<MatchingApplicationEntity> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state);
    List<MatchingApplicationEntity> getByMatchingIdAndStateEquals(Long matchingId, ApplicationStateEnum state);
}