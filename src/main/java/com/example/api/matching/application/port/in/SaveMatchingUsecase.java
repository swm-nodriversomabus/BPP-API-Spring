package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.SaveMatchingDto;

public interface SaveMatchingUsecase {
    FindMatchingDto createMatching(SaveMatchingDto createMatchingDto);
    FindMatchingDto updateMatching(Long matchingId, SaveMatchingDto matchingDto);
}