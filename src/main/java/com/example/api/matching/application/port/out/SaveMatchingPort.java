package com.example.api.matching.application.port.out;

import com.example.api.matching.dto.MatchingDto;

public interface SaveMatchingPort {
    void createMatching(MatchingDto matching);
}