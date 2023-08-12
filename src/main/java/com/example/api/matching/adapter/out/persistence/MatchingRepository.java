package com.example.api.matching.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingRepository extends JpaRepository<MatchingEntity, Long> {
    List<MatchingEntity> getAllBy();
    Optional<MatchingEntity> getMatchingByMatchingId(Long matchingId);
    void deleteAllBy();
    void deleteByMatchingId(Long matchingId);
}