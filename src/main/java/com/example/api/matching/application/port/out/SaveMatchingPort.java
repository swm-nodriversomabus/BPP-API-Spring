package com.example.api.matching.application.port.out;

import com.example.api.matching.domain.MatchingEntity;
import org.springframework.stereotype.Component;

@Component
public interface SaveMatchingPort {
    MatchingEntity createMatching(MatchingEntity matching);
    MatchingEntity updateMatching(MatchingEntity matching);
}