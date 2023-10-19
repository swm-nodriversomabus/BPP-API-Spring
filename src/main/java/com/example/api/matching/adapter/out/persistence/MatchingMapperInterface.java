package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.LikeDto;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.dto.SaveMatchingDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MatchingMapperInterface {
    Matching toDomain(SaveMatchingDto matchingDto);
    MatchingApplication toDomain(SaveMatchingApplicationDto matchingApplicationDto);
    MatchingEntity toEntity(Matching matching);
    MatchingApplicationEntity toEntity(MatchingApplication matchingApplication);
    LikeEntity toEntity(LikeDto likeDto);
    Matching toDomain(MatchingEntity matchingEntity);
    MatchingApplication toDomain(MatchingApplicationEntity matchingApplicationEntity);
    FindMatchingDto toDto(Matching matching);
    SaveMatchingApplicationDto toDto(MatchingApplication matchingApplication);
    FindMatchingDto toDto(MatchingEntity matchingEntity);
    LikeDto toDto(LikeEntity likeEntity);
}