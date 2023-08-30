package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.MatchingApplicationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingApplicationRepository extends JpaRepository<MatchingApplicationEntity, MatchingApplicationPK> {
}