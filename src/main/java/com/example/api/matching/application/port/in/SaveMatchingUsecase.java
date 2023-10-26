package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.SaveMatchingDto;

import java.util.UUID;

public interface SaveMatchingUsecase {
    FindMatchingDto createMatching(UUID writerId, SaveMatchingDto createMatchingDto);
    FindMatchingDto updateMatching(Long matchingId, SaveMatchingDto matchingDto);
}