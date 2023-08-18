package com.example.api.preference.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingPreferenceRepository extends JpaRepository<MatchingPreferenceEntity, MatchingPreferencePK> {
    @Query("SELECT M FROM MatchingPreferenceEntity M WHERE M.matchingId = :matchingId")
    public MatchingPreferenceEntity getMatchingPreferenceId(@Param("matchingId") Long matchingId);
}