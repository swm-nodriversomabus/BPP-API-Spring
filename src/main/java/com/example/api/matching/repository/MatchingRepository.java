package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MatchingRepository extends JpaRepository<MatchingEntity, Long> {
    List<MatchingEntity> getAllBy();
    Optional<MatchingEntity> getMatchingEntityByMatchingId(Long matchingId);
    List<MatchingEntity> getMatchingEntitiesByIsActive(Boolean isActive);
    void deleteAllBy();
    void deleteByMatchingId(Long matchingId);
}