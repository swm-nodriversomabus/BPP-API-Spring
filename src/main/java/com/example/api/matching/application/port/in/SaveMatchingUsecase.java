package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.MatchingDto;

public interface SaveMatchingUsecase {
    MatchingDto createMatching(MatchingDto createMatchingDto);
    MatchingDto updateMatching(Long matchingId, MatchingDto matchingDto);
}