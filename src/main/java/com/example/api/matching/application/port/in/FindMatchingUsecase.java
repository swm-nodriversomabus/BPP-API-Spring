package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.MatchingDto;

import java.util.List;
import java.util.Optional;

public interface FindMatchingUsecase {
    List<MatchingDto> getAll();
    Optional<MatchingDto> getMatchingById(Long matchingId);
    List<MatchingDto> getMatchingByIsActive(Boolean isActive);
    List<MatchingDto> getRecommendedMatchingList(Long userId);
}