package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.MatchingDto;

import java.util.List;
import java.util.Optional;

public interface MatchingUsecase {
    MatchingDto createMatching(MatchingDto createMatchingDto);
    List<MatchingDto> getAll();
    Optional<MatchingDto> getMatchingById(Long matchingId);
    int getLikeCount(Long matchingId);
    MatchingDto updateMatching(MatchingDto matchingDto);
    void toggleLike(Long userId, Long matchingId);
    void deleteAll();
    void deleteMatching(Long matchingId);
}