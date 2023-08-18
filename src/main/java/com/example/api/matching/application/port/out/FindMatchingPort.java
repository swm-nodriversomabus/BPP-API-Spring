package com.example.api.matching.application.port.out;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FindMatchingPort {
    List<MatchingEntity> getAllBy();
    Optional<MatchingEntity> getMatchingByMatchingId(Long matchingId);
    List<MatchingEntity> getMatchingByIsActive(Boolean isActive);
}