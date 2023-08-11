package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingEntity;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchingMapper {
    public Matching fromDtoToCreateDomain(MatchingDto matchingDto) {
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

    public Matching fromDtoToUpdateDomain(MatchingDto matchingDto) {
        return Matching.builder()
                .matchingId(matchingDto.getMatchingId())
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
    
    public MatchingEntity fromDomainToCreateEntity(Matching matching) {
        return MatchingEntity.builder()
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

    public MatchingEntity fromDomainToUpdateEntity(Matching matching) {
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
}