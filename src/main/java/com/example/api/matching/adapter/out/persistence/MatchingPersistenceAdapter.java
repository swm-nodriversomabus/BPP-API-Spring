package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.application.port.out.*;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.repository.LikeRepository;
import com.example.api.matching.repository.MatchingRepository;
import com.example.api.matching.type.MatchingTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@ComponentScan
public class MatchingPersistenceAdapter implements SaveMatchingPort, FindMatchingPort, DeleteMatchingPort, LikePort {
    private final MatchingMapperInterface matchingMapper;
    private final MatchingRepository matchingRepository;
    private final LikeRepository likeRepository;
    
    @Override
    public Matching createMatching(Matching matching) {
        MatchingEntity matchingData = matchingRepository.save(matchingMapper.toEntity(matching));
        return matchingMapper.toDomain(matchingData);
    }
    
    @Override
    public List<MatchingEntity> getAllBy() {
        return matchingRepository.getAllBy();
    }
    
    @Override
    public List<MatchingEntity> getDiningMatchingList() {
        return matchingRepository.getByType(MatchingTypeEnum.Dining);
    }
    
    @Override
    public Optional<MatchingEntity> getByMatchingId(Long matchingId) {
        return matchingRepository.getByMatchingId(matchingId);
    }
    
    @Override
    public List<MatchingEntity> getByWriterId(UUID userId) {
        return matchingRepository.getByWriterId(userId);
    }
    
    @Override
    public List<MatchingEntity> getByIsActive(Boolean isActive) {
        return matchingRepository.getByIsActive(isActive);
    }
    
    @Override
    public int getLikeCount(Long matchingId) {
        return likeRepository.countByMatchingId(matchingId);
    }
    
    @Override
    public Matching updateMatching(Long matchingId, Matching matching) {
        matching.setMatchingId(matchingId);
        MatchingEntity matchingData = matchingRepository.save(matchingMapper.toEntity(matching));
        return matchingMapper.toDomain(matchingData);
    }
    
    @Override
    public void toggleLike(LikeEntity likeEntity) {
        LikePK likePK = new LikePK(likeEntity.getUserId(), likeEntity.getMatchingId());
        Optional<LikeEntity> likeData = likeRepository.findById(likePK);
        if (likeData.isEmpty()) {
            likeRepository.save(likeEntity);
        }
        else {
            likeRepository.delete(likeEntity);
        }
    }
    
    @Override
    public void deleteAllBy() {
        matchingRepository.deleteAllBy();
    }
    
    @Override
    public void deleteByMatchingId(Long matchingId) {
        matchingRepository.deleteByMatchingId(matchingId);
    }
}