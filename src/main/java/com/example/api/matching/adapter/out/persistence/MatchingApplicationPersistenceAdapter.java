package com.example.api.matching.adapter.out.persistence;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.repository.MatchingApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
@ComponentScan
public class MatchingApplicationPersistenceAdapter implements MatchingApplicationPort {
    private final MatchingMapperInterface matchingMapper;
    private final MatchingApplicationRepository matchingApplicationRepository;
    
    @Override
    public MatchingApplication saveMatchingApplication(MatchingApplication matchingApplication) {
        MatchingApplicationEntity matchingApplicationData = matchingApplicationRepository.save(matchingMapper.toEntity(matchingApplication));
        return matchingMapper.toDomain(matchingApplicationData);
    }

    @Override
    public Optional<MatchingApplicationEntity> getByMatchingApplicationPK(MatchingApplicationPK matchingApplicationPK) {
        return matchingApplicationRepository.getByUserIdAndMatchingId(matchingApplicationPK.getUserId(), matchingApplicationPK.getMatchingId());
    }
    
    @Override
    public List<MatchingApplicationEntity> getByUserIdIsAndStateEquals(UUID userId, ApplicationStateEnum state) {
        return matchingApplicationRepository.getByUserIdIsAndStateEquals(userId, state);
    }
    
    @Override
    public List<MatchingApplicationEntity> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state) {
        return matchingApplicationRepository.getByMatchingIdAndStateEquals(matchingId, state);
    }
}