package com.example.api.matching.service;

import com.example.api.matching.application.port.in.MatchingUsecase;
import com.example.api.matching.dto.MatchingDto;
import com.example.api.matching.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService implements MatchingUsecase {
    private final MatchingRepository matchingRepository;

    @Override
    @Transactional
    public void createMatching(MatchingDto matchingDto) {
        matchingRepository.createMatching(matchingDto);
    }

    @Override
    public List<MatchingDto> getAll() {
        return matchingRepository.getAll();
    }

    @Override
    public MatchingDto getMatchingById(Long matchingId) {
        return matchingRepository.getMatchingById(matchingId);
    }

    @Override
    @Transactional
    public void updateMatching(Long matchingId, MatchingDto matchingDto) {

    }

    @Override
    @Transactional
    public void deleteAll() {

    }

    @Override
    @Transactional
    public void deleteMatching(Long matchingId) {

    }
}