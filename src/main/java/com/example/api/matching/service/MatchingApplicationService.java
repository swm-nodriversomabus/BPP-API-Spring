package com.example.api.matching.service;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.MatchingApplicationDto;
import com.example.api.matching.dto.MatchingDto;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingApplicationService implements MatchingApplicationUsecase {
    private UserMapperInterface userMapper;
    private MatchingMapperInterface matchingMapper;
    private final FindUserPort findUserPort;
    private final FindMatchingPort findMatchingPort;
    private final MatchingApplicationPort matchingApplicationPort;
    
    @Override
    @Transactional
    public MatchingApplicationDto createMatchingApplication(MatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingApplicationPort.createMatchingApplication(matchingMapper.toDomain(matchingApplicationDto));
        return matchingMapper.toDto(matchingApplication);
    }
    
    @Override
    public List<MatchingDto> getByUserIdIsAndStateEquals(Long userId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByUserIdIsAndStateEquals(userId, state);
        List<MatchingDto> matchingData = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            matchingData.add(matchingMapper.toDto(findMatchingPort.getMatchingByMatchingId(matchingPair.getMatchingId()).orElseThrow()));
        }
        return matchingData;
    }

    @Override
    public List<UserDto> getByMatchingIdIsAndStateEquals(Long matchingId, ApplicationStateEnum state) {
        List<MatchingApplicationEntity> matchingPairList = matchingApplicationPort.getByMatchingIdIsAndStateEquals(matchingId, state);
        List<UserDto> userData = new ArrayList<>();
        for (MatchingApplicationEntity matchingPair: matchingPairList) {
            userData.add(userMapper.toDto(findUserPort.getUserByUserId(matchingPair.getUserId()).orElseThrow()));
        }
        return userData;
    }
    
    @Override
    @Transactional
    public MatchingApplicationDto updateMatchingApplication(MatchingApplicationDto matchingApplicationDto) {
        MatchingApplication matchingApplication = matchingApplicationPort.updateMatchingApplication(matchingMapper.toDomain(matchingApplicationDto));
        return matchingMapper.toDto(matchingApplication);
    }
}