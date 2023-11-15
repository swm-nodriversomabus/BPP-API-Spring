package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
    List<AccommodationEntity> getAllBy();
    Optional<AccommodationEntity> getByMatchingId(Long matchingId);
}