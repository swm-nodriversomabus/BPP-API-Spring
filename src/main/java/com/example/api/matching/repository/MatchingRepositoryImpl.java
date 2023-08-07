package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MatchingRepositoryImpl implements MatchingRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createMatching(MatchingEntity matchingEntity) {
        em.persist(matchingEntity);
    }
    
    @Override
    public void updateMatching(Long matchingId, MatchingEntity matchingEntity) {
        MatchingEntity matching = em.find(MatchingEntity.class, matchingId);
        matching.setWriterId(matchingEntity.getWriterId());
        matching.setType(matchingEntity.getType());
        matching.setTitle(matchingEntity.getTitle());
        matching.setPlace(matchingEntity.getPlace());
        matching.setContent(matchingEntity.getContent());
        matching.setStartDate(matchingEntity.getStartDate());
        matching.setEndDate(matchingEntity.getEndDate());
        matching.setMaxMember(matchingEntity.getMaxMember());
        matching.setMinusAge(matchingEntity.getMinusAge());
        matching.setPlusAge(matchingEntity.getPlusAge());
        matching.setReadCount(matchingEntity.getReadCount());
        matching.setLikeCount(matchingEntity.getLikeCount());
        matching.setIsActive(matchingEntity.getIsActive());
    }
}