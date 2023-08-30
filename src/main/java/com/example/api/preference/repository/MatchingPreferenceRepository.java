package com.example.api.preference.repository;

import com.example.api.preference.adapter.out.persistence.MatchingPreferenceEntity;
import com.example.api.preference.adapter.out.persistence.MatchingPreferencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingPreferenceRepository extends JpaRepository<MatchingPreferenceEntity, MatchingPreferencePK> {
    @Query("SELECT M FROM MatchingPreferenceEntity M WHERE M.matchingId = :matchingId")
    public MatchingPreferenceEntity getMatchingPreferenceId(@Param("matchingId") Long matchingId);
}