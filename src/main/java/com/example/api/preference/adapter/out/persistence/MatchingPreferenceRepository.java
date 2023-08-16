package com.example.api.preference.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingPreferenceRepository extends JpaRepository<MatchingPreferenceEntity, MatchingPreferencePK> {
}