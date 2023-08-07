package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;

public interface MatchingRepositoryCustom {
    void createMatching(MatchingEntity matchingEntity);
    void updateMatching(Long matchingId, MatchingEntity matchingEntity);
}