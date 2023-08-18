package com.example.api.user.application.port.in;

import com.example.api.matching.dto.MatchingDto;

import java.util.List;

public interface RecommendedMatchingUsecase {
    List<MatchingDto> getRecommendedMatchingList(Long userId);
}