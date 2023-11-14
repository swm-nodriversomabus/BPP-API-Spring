package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
    Optional<AccommodationEntity> getByMatchingId(Long matchingId);
}