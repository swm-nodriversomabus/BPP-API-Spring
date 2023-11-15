package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.domain.Accommodation;
import com.example.api.matching.domain.AccommodationMatching;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MatchingMapperInterface {
    Matching toDomain(SaveMatchingDto matchingDto);
    MatchingApplication toDomain(SaveMatchingApplicationDto matchingApplicationDto);
    Accommodation toDomain(AccommodationDto accommodationDto);
    MatchingEntity toEntity(Matching matching);
    MatchingApplicationEntity toEntity(MatchingApplication matchingApplication);
    AccommodationEntity toEntity(Accommodation accommodation);
    LikeEntity toEntity(LikeDto likeDto);
    Matching toDomain(MatchingEntity matchingEntity);
    AccommodationMatching toAccommodationDomain(MatchingEntity matchingEntity);
    MatchingApplication toDomain(MatchingApplicationEntity matchingApplicationEntity);
    Accommodation toDomain(AccommodationEntity accommodationEntity);
    FindMatchingDto toDto(Matching matching);
    FindMatchingApplicationDto toDto(MatchingApplication matchingApplication);
    FindMatchingDto toDto(MatchingEntity matchingEntity);
    AccommodationDto toDto(Accommodation accommodation);
    AccommodationMatchingDto toDto(AccommodationMatching accommodationMatching);
    AccommodationMatchingDto toDto(FindMatchingDto matchingDto);
    LikeDto toDto(LikeEntity likeEntity);
}