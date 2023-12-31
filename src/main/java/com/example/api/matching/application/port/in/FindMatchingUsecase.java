package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.FindMatchingDto;

import java.util.List;
import java.util.UUID;

public interface FindMatchingUsecase {
    List<FindMatchingDto> getAll();
    List<FindMatchingDto> getDiningMatchingList();
    FindMatchingDto getMatchingById(Long matchingId);
    List<FindMatchingDto> getMatchingByWriterId(UUID userId);
    List<FindMatchingDto> getNearMatching(Double latitude, Double longitude);
    List<FindMatchingDto> getMatchingByIsActive(Boolean isActive);
    List<FindMatchingDto> getRecommendedMatchingList(UUID userId);
}