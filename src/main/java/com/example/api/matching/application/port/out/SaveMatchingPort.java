package com.example.api.matching.application.port.out;

import com.example.api.matching.domain.Matching;
import org.springframework.stereotype.Component;

@Component
public interface SaveMatchingPort {
    Matching createMatching(Matching matching);
    Matching updateMatching(Long matchingId, Matching matching);
}