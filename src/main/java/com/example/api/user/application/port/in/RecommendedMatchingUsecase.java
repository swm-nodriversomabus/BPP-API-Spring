package com.example.api.user.application.port.in;

import com.example.api.matching.dto.FindMatchingDto;

import java.util.List;

public interface RecommendedMatchingUsecase {
    List<FindMatchingDto> getRecommendedMatchingList(Long userId);
}