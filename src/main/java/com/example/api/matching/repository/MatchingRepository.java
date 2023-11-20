package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.type.MatchingTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchingRepository extends JpaRepository<MatchingEntity, Long> {
    List<MatchingEntity> getAllBy();
    Optional<MatchingEntity> getByMatchingId(Long matchingId);
    List<MatchingEntity> getByWriterId(UUID userId);
    List<MatchingEntity> getByLatitudeGreaterThanEqualAndLatitudeLessThanEqualAndLongitudeGreaterThanEqualAndLongitudeLessThanEqual(Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude);
    List<MatchingEntity> getByType(MatchingTypeEnum type);
    List<MatchingEntity> getByIsActive(Boolean isActive);
    void deleteAllBy();
    void deleteByMatchingId(Long matchingId);
}