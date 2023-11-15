package com.example.api.matching.service;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.type.Pair;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.DeleteMatchingUsecase;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.LikeUsecase;
import com.example.api.matching.application.port.out.*;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.application.port.in.SaveMatchingUsecase;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.LikeDto;
import com.example.api.matching.dto.SaveMatchingDto;
import com.example.api.preference.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MatchingService implements SaveMatchingUsecase, FindMatchingUsecase, DeleteMatchingUsecase, LikeUsecase {
    private final PreferenceService preferenceService;
    private final SaveMatchingPort saveMatchingPort;
    private final FindMatchingPort findMatchingPort;
    private final DeleteMatchingPort deleteMatchingPort;
    private final MatchingApplicationPort matchingApplicationPort;
    private final LikePort likePort;
    private final MatchingMapperInterface matchingMapper;

    @Override
    @Transactional
    public FindMatchingDto createMatching(UUID writerId, SaveMatchingDto matchingDto) {
        Matching matching = matchingMapper.toDomain(matchingDto);
        matching.setWriterId(writerId);
        if (matching.getStartDate().isAfter(matching.getEndDate())) {
            log.error("MatchingService::createMatching: Invalid duration");
            throw new CustomException(ErrorCodeEnum.INVALID_DURATION);
        }
        return matchingMapper.toDto(saveMatchingPort.createMatching(matching));
    }
    
    public List<FindMatchingDto> setCurrentMember(List<MatchingEntity> matchingEntities) {
        List<FindMatchingDto> matchingList = new ArrayList<>();
        for (MatchingEntity matchingData: matchingEntities) {
            Matching matching = matchingMapper.toDomain(matchingData);
            matching.setCurrentMember(matchingApplicationPort.getByMatchingIdAndStateEquals(matchingData.getMatchingId(), ApplicationStateEnum.Approved).size() + 1);
            matchingList.add(matchingMapper.toDto(matching));
        }
        return matchingList;
    }

    @Override
    public List<FindMatchingDto> getAll() {
        return this.setCurrentMember(findMatchingPort.getAllBy());
    }
    
    @Override
    public List<FindMatchingDto> getDiningMatchingList() {
        return this.setCurrentMember(findMatchingPort.getDiningMatchingList());
    }

    @Override
    public FindMatchingDto getMatchingById(Long matchingId) {
        Optional<MatchingEntity> matchingEntity = findMatchingPort.getByMatchingId(matchingId);
        if (matchingEntity.isEmpty()) {
            return null;
        }
        Matching matching = matchingMapper.toDomain(matchingEntity.get());
        matching.setCurrentMember(matchingApplicationPort.getByMatchingIdAndStateEquals(matchingId, ApplicationStateEnum.Approved).size() + 1);
        return matchingMapper.toDto(matching);
    }

    @Override
    public List<FindMatchingDto> getMatchingByWriterId(UUID userId) {
        return this.setCurrentMember(findMatchingPort.getByWriterId(userId));
    }

    @Override
    public List<FindMatchingDto> getMatchingByIsActive(Boolean isActive) {
        return this.setCurrentMember(findMatchingPort.getByIsActive(isActive));
    }

    @Override
    public List<FindMatchingDto> getRecommendedMatchingList(UUID userId) {
        List<FindMatchingDto> activeMatchingList = this.getMatchingByIsActive(true); // 유효한 매칭만 뽑아옴
        List<Pair<Long, Integer>> matchingScoreList = new ArrayList<>();
        for (FindMatchingDto matchingData: activeMatchingList) { // 매칭별로 유사도 점수를 계산함
            Long matchingId = matchingData.getMatchingId();
            Integer matchingScore = preferenceService.getMatchingScore(userId, matchingId);
            matchingScoreList.add(new Pair<>(matchingId, matchingScore));
        }
        matchingScoreList.sort(Comparator.comparing(Pair::getSecond));
        List<FindMatchingDto> sortedMatchingList = new ArrayList<>();
        try {
            for (Pair<Long, Integer> matchingData: matchingScoreList) { // 유사도가 높은 순서로 정렬한 후 반환
                FindMatchingDto findMatchingDto = this.getMatchingById(matchingData.getFirst());
                if (findMatchingDto != null) {
                    sortedMatchingList.add(findMatchingDto);
                    if (sortedMatchingList.size() == 10) {
                        break;
                    }
                    MatchingApplicationPK matchingApplicationPK = MatchingApplicationPK.builder()
                                    .userId(userId)
                                    .matchingId(findMatchingDto.getMatchingId())
                                    .build();
                    if (matchingApplicationPort.getByMatchingApplicationPK(matchingApplicationPK).isEmpty()) {
                        sortedMatchingList.add(findMatchingDto);
                    }
                }
            }
        } catch (Exception e) {
            log.error("MatchingService::getRecommendedMatchingList: Cannot find matchingData");
            return new ArrayList<>();
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