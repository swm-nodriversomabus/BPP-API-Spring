package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.repository.MatchingApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchingApplicationPersistenceAdapter implements MatchingApplicationPort {
    private final MatchingApplicationRepository matchingApplicationRepository;
    
    /*
    public MatchingApplication createMatchingApplication(MatchingApplication matchingApplication) {
        MatchingApplicationEntity matchingApplicationData = matchingApplicationRepository.save(matchingApplication);
    }*/
}