package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.application.port.out.DeleteMatchingPort;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.LikePort;
import com.example.api.matching.application.port.out.SaveMatchingPort;
import com.example.api.matching.domain.MatchingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchingPersistenceAdapter implements SaveMatchingPort, FindMatchingPort, DeleteMatchingPort, LikePort {
    private final MatchingRepository matchingRepository;
    
    @Override
    public MatchingEntity createMatching(MatchingEntity matchingEntity) {
        return matchingRepository.save(matchingEntity);
    }
    
    @Override
    public List<MatchingEntity> getAllBy() {
        return matchingRepository.getAllBy();
    }
    
    @Override
    public Optional<MatchingEntity> getMatchingByMatchingId(Long matchingId) {
        return matchingRepository.getMatchingByMatchingId(matchingId);
    }
    
    @Override
    public int getLikeCount(Long matchingId) {
        return 0;
    }
    
    @Override
    public MatchingEntity updateMatching(MatchingEntity matchingEntity) {
        return matchingRepository.save(matchingEntity);
    }
    
    @Override
    public void toggleLike(Long userId, Long matchingId) {
        
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