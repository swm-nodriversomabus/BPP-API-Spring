package com.example.api.matching.service;

import com.example.api.common.type.Pair;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.DeleteMatchingUsecase;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.LikeUsecase;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.application.port.in.SaveMatchingUsecase;
import com.example.api.matching.application.port.out.DeleteMatchingPort;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.LikePort;
import com.example.api.matching.application.port.out.SaveMatchingPort;
import com.example.api.matching.dto.MatchingDto;
import com.example.api.preference.service.PreferenceService;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService implements
        SaveMatchingUsecase, FindMatchingUsecase, DeleteMatchingUsecase, LikeUsecase, RecommendedMatchingUsecase {
    private final PreferenceService preferenceService;
    private final MatchingMapperInterface matchingMapper;
    private final SaveMatchingPort saveMatchingPort;
    private final FindMatchingPort findMatchingPort;
    private final DeleteMatchingPort deleteMatchingPort;
    private final LikePort likePort;

    @Override
    @Transactional
    public MatchingDto createMatching(MatchingDto matchingDto) {
        Matching matching = saveMatchingPort.createMatching(matchingMapper.toDomain(matchingDto));
        return matchingMapper.toDto(matching);
    }

    @Override
    public List<MatchingDto> getAll() {
        return findMatchingPort.getAllBy().stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatchingDto> getMatchingById(Long matchingId) {
        return findMatchingPort.getMatchingByMatchingId(matchingId)
                .map(MatchingEntity::toDto);
    }
    
    @Override
    public List<MatchingDto> getMatchingByIsActive(Boolean isActive) {
        return findMatchingPort.getMatchingByIsActive(isActive).stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<MatchingDto> getRecommendedMatchingList(Long userId) {
        List<MatchingDto> activeMatchingList = this.getMatchingByIsActive(true);
        List<Pair<Long, Integer>> matchingScoreList = new ArrayList<>();
        for (MatchingDto matchingData: activeMatchingList) {
            Long matchingId = matchingData.getMatchingId();
            Integer matchingScore = preferenceService.getMatchingScore(userId, matchingId);
            matchingScoreList.add(new Pair<>(matchingId, matchingScore));
        }
        matchingScoreList.sort(new Comparator<>() {
            @Override
            public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
                return o1.getSecond().compareTo(o2.getSecond());
            }
        });
        List<MatchingDto> sortedMatchingList = new ArrayList<>();
        for (Pair<Long, Integer> matchingData: matchingScoreList) {
            sortedMatchingList.add(this.getMatchingById(matchingData.getFirst()).orElseThrow());
        }
        return sortedMatchingList;
    }
    
    @Override
    public int getLikeCount(Long matchingId) {
        return likePort.getLikeCount(matchingId);
    }

    @Override
    @Transactional
    public MatchingDto updateMatching(Long matchingId, MatchingDto matchingDto) {
        matchingDto.setMatchingId(matchingId);
        Matching matching = saveMatchingPort.updateMatching(matchingMapper.toDomain(matchingDto));
        return matchingMapper.toDto(matching);
    }
    
    @Override
    @Transactional
    public void toggleLike(Long userId, Long matchingId) {
        likePort.toggleLike(userId, matchingId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        deleteMatchingPort.deleteAllBy();
    }

    @Override
    @Transactional
    public void deleteMatching(Long matchingId) {
        deleteMatchingPort.deleteByMatchingId(matchingId);
    }
}