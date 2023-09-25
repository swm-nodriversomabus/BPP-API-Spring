package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.FindMatchingDto;

import java.util.List;
import java.util.Optional;

public interface FindMatchingUsecase {
    List<FindMatchingDto> getAll();
    Optional<FindMatchingDto> getMatchingById(Long matchingId);
    List<FindMatchingDto> getMatchingByIsActive(Boolean isActive);
    List<FindMatchingDto> getRecommendedMatchingList(Long userId);
}