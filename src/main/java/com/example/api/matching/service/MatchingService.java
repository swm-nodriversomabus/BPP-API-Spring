package com.example.api.matching.service;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.application.port.in.MatchingUsecase;
import com.example.api.matching.dto.MatchingDto;
import com.example.api.matching.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService implements MatchingUsecase {
    private final MatchingRepository matchingRepository;

    @Override
    @Transactional
    public void createMatching(MatchingDto matchingDto) {
        matchingRepository.createMatching(matchingDto.toEntity());
    }

    @Override
    public List<MatchingDto> getAll() {
        return matchingRepository.getAllBy().stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatchingDto> getMatchingById(Long matchingId) {
        return matchingRepository.getMatchingByMatchingId(matchingId)
                .map(MatchingEntity::toDto);
    }

    @Override
    @Transactional
    public void updateMatching(Long matchingId, MatchingDto matchingDto) {
        matchingRepository.updateMatching(matchingId, matchingDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteAll() {
        matchingRepository.deleteAllBy();
    }

    @Override
    @Transactional
    public void deleteMatching(Long matchingId) {
        matchingRepository.deleteByMatchingId(matchingId);
    }
}