package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
}