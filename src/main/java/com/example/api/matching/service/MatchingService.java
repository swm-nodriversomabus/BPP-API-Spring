package com.example.api.matching.service;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.type.Pair;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.DeleteMatchingUsecase;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.LikeUsecase;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.application.port.in.SaveMatchingUsecase;
import com.example.api.matching.application.port.out.DeleteMatchingPort;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.LikePort;
import com.example.api.matching.application.port.out.SaveMatchingPort;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.LikeDto;
import com.example.api.matching.dto.SaveMatchingDto;
import com.example.api.preference.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService implements SaveMatchingUsecase, FindMatchingUsecase, DeleteMatchingUsecase, LikeUsecase {
    private final PreferenceService preferenceService;
    private final MatchingMapperInterface matchingMapper;
    private final SaveMatchingPort saveMatchingPort;
    private final FindMatchingPort findMatchingPort;
    private final DeleteMatchingPort deleteMatchingPort;
    private final LikePort likePort;

    @Override
    @Transactional
    public FindMatchingDto createMatching(SaveMatchingDto matchingDto) {
        Matching matching = matchingMapper.toDomain(matchingDto);
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingService::createMatching: Authentication is needed.");
            return matchingMapper.toDto(matching);
        }
        matching.setWriterId(securityUser.getUserId());
        matching = saveMatchingPort.createMatching(matching);
        return matchingMapper.toDto(matching);
    }

    @Override
    public List<FindMatchingDto> getAll() {
        return findMatchingPort.getAllBy().stream()
                .map(matchingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FindMatchingDto> getMatchingById(Long matchingId) {
        return findMatchingPort.getByMatchingId(matchingId)
                .map(matchingMapper::toDto);
    }
    
    @Override
    public List<FindMatchingDto> getMatchingByWriterId() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingService::getMatchingByWriterId: Authentication is needed.");
            return new ArrayList<>();
        }
        UUID userId = securityUser.getUserId();
        return findMatchingPort.getByWriterId(userId).stream()
                .map(matchingMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FindMatchingDto> getMatchingByIsActive(Boolean isActive) {
        return findMatchingPort.getByIsActive(isActive).stream()
                .map(matchingMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FindMatchingDto> getRecommendedMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingService::getRecommendedMatchingList: Authentication is needed.");
            return new ArrayList<>();
        }
        List<FindMatchingDto> activeMatchingList = this.getMatchingByIsActive(true); // 유효한 매칭만 뽑아옴
        List<Pair<Long, Integer>> matchingScoreList = new ArrayList<>();
        for (FindMatchingDto matchingData: activeMatchingList) { // 매칭별로 유사도 점수를 계산함
            Long matchingId = matchingData.getMatchingId();
            Integer matchingScore = preferenceService.getMatchingScore(matchingId);
            matchingScoreList.add(new Pair<>(matchingId, matchingScore));
        }
        matchingScoreList.sort(Comparator.comparing(Pair::getSecond));
        List<FindMatchingDto> sortedMatchingList = new ArrayList<>();
        try {
            for (Pair<Long, Integer> matchingData: matchingScoreList) { // 유사도가 높은 순서로 정렬한 후 반환
                sortedMatchingList.add(this.getMatchingById(matchingData.getFirst()).orElseThrow());
            }
        } catch (Exception e) {
            log.error("MatchingService::getRecommendedMatchingList: Cannot find matchingData");
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
        likePort.toggleLike(matchingMapper.toEntity(likeDto));
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