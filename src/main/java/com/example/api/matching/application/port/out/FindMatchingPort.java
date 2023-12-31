package com.example.api.matching.application.port.out;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindMatchingPort {
    List<MatchingEntity> getAllBy();
    List<MatchingEntity> getDiningMatchingList();
    Optional<MatchingEntity> getByMatchingId(Long matchingId);
    List<MatchingEntity> getByWriterId(UUID userId);
    List<MatchingEntity> getByPlaceCoordinate(Double latitude, Double longitude);
    List<MatchingEntity> getByIsActive(Boolean isActive);
}