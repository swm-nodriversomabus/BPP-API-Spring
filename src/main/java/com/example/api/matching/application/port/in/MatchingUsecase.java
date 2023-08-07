package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.MatchingDto;

import java.util.List;

public interface MatchingUsecase {
    void createMatching(MatchingDto createMatchingDto);
    List<MatchingDto> getAll();
    MatchingDto getMatchingById(Long matchingId);
    void updateMatching(Long matchingId, MatchingDto matchingDto);
    void deleteAll();
    void deleteMatching(Long matchingId);
}