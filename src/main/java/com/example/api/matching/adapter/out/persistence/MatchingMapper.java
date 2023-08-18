package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.domain.Matching;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchingMapper {
    public Matching fromDtoToDomain(MatchingDto matchingDto) {
        return Matching.builder()
                .writerId(matchingDto.getWriterId())
                .type(matchingDto.getType())
                .title(matchingDto.getTitle())
                .content(matchingDto.getContent())
                .place(matchingDto.getPlace())
                .startDate(matchingDto.getStartDate())
                .endDate(matchingDto.getEndDate())
                .maxMember(matchingDto.getMaxMember())
                .minusAge(matchingDto.getMinusAge())
                .plusAge(matchingDto.getPlusAge())
                .readCount(matchingDto.getReadCount())
                .isActive(matchingDto.getIsActive())
                .build();
    }
    
    public MatchingEntity fromDomainToEntity(Matching matching) {
        return MatchingEntity.builder()
                .matchingId(matching.getMatchingId())
                .writerId(matching.getWriterId())
                .type(matching.getType())
                .title(matching.getTitle())
                .content(matching.getContent())
                .place(matching.getPlace())
                .startDate(matching.getStartDate())
                .endDate(matching.getEndDate())
                .maxMember(matching.getMaxMember())
                .minusAge(matching.getMinusAge())
                .plusAge(matching.getPlusAge())
                .readCount(matching.getReadCount())
                .isActive(matching.getIsActive())
                .build();
    }
    
    public Matching fromEntityToDomain(MatchingEntity matchingEntity) {
        return Matching.builder()
                .matchingId(matchingEntity.getMatchingId())
                .writerId(matchingEntity.getWriterId())
                .type(matchingEntity.getType())
                .title(matchingEntity.getTitle())
                .content(matchingEntity.getContent())
                .place(matchingEntity.getPlace())
                .startDate(matchingEntity.getStartDate())
                .endDate(matchingEntity.getEndDate())
                .maxMember(matchingEntity.getMaxMember())
                .minusAge(matchingEntity.getMinusAge())
                .plusAge(matchingEntity.getPlusAge())
                .readCount(matchingEntity.getReadCount())
                .createdAt(matchingEntity.getCreatedAt())
                .updatedAt(matchingEntity.getUpdatedAt())
                .isActive(matchingEntity.getIsActive())
                .build();
    }
    
    public MatchingDto fromDomainToDto(Matching matching) {
        return MatchingDto.builder()
                .matchingId(matching.getMatchingId())
                .writerId(matching.getWriterId())
                .type(matching.getType())
                .title(matching.getTitle())
                .content(matching.getContent())
                .place(matching.getPlace())
                .startDate(matching.getStartDate())
                .endDate(matching.getEndDate())
                .maxMember(matching.getMaxMember())
                .minusAge(matching.getMinusAge())
                .plusAge(matching.getPlusAge())
                .readCount(matching.getReadCount())
                .isActive(matching.getIsActive())
                .build();
    }
}