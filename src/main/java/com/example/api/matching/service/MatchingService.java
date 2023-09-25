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
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.LikeDto;
import com.example.api.matching.dto.SaveMatchingDto;
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
    public FindMatchingDto createMatching(SaveMatchingDto matchingDto) {
        Matching matching = saveMatchingPort.createMatching(matchingMapper.toDomain(matchingDto));
        return matchingMapper.toDto(matching);
    }

    @Override
    public List<FindMatchingDto> getAll() {
        return findMatchingPort.getAllBy().stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FindMatchingDto> getMatchingById(Long matchingId) {
        return findMatchingPort.getMatchingByMatchingId(matchingId)
                .map(MatchingEntity::toDto);
    }
    
    @Override
    public List<FindMatchingDto> getMatchingByIsActive(Boolean isActive) {
        return findMatchingPort.getMatchingByIsActive(isActive).stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FindMatchingDto> getRecommendedMatchingList(Long userId) {
        List<FindMatchingDto> activeMatchingList = this.getMatchingByIsActive(true);
        List<Pair<Long, Integer>> matchingScoreList = new ArrayList<>();
        for (FindMatchingDto matchingData: activeMatchingList) {
            Long matchingId = matchingData.getMatchingId();
            Integer matchingScore = preferenceService.getMatchingScore(userId, matchingId);
            matchingScoreList.add(new Pair<>(matchingId, matchingScore));
        }
        matchingScoreList.sort(Comparator.comparing(Pair::getSecond));
        List<FindMatchingDto> sortedMatchingList = new ArrayList<>();
        for (Pair<Long, Integer> matchingData: matchingScoreList) {
            sortedMatchingList.add(this.getMatchingById(matchingData.getFirst()).orElseThrow());
        }
        return sortedMatchingList;
    }
    // 특성별로 가중치 추가하기
    // 이전의 여행 히스토리 참고하기
    
    @Override
    public int getLikeCount(Long matchingId) {
        return likePort.getLikeCount(matchingId);
    }

    @Override
    @Transactional
    public FindMatchingDto updateMatching(Long matchingId, SaveMatchingDto matchingDto) {
        Matching matching = saveMatchingPort.updateMatching(matchingId, matchingMapper.toDomain(matchingDto));
        return matchingMapper.toDto(matching);
    }
    
    @Override
    @Transactional
    public void toggleLike(LikeDto likeDto) {
        likePort.toggleLike(likeDto.toEntity());
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