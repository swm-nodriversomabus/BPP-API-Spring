package com.example.api.matching.repository;

import com.example.api.matching.dto.MatchingDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchingRepository {
    @Autowired
    private EntityManager em;
    
    public void createMatching(MatchingDto matchingDto) {
        em.persist(matchingDto);
    }
    
    public List<MatchingDto> getAll() {
        return em.createQuery("SELECT M FROM MatchingEntity M", MatchingDto.class).getResultList();
    }
    
    public MatchingDto getMatchingById(Long matchingId) {
        return em.find(MatchingDto.class, matchingId);
    }
}