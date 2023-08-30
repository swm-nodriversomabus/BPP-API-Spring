package com.example.api.matching.application.port.out;

import com.example.api.matching.domain.Matching;

public interface SaveMatchingPort {
    Matching createMatching(Matching matching);
    Matching updateMatching(Matching matching);
}