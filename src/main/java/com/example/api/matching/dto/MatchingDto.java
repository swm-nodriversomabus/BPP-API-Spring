package com.example.api.matching.dto;

import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingEntity;
import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchingDto {
    private Long matchingId;
    
    @NotEmpty
    private Long writerId;
    
    @NotEmpty
    private MatchingTypeEnum type;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String place;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private LocalDateTime startDate;
    
    @NotEmpty
    private LocalDateTime endDate;
    
    @NotEmpty
    private Integer maxMember;
    
    @NotEmpty
    private Integer minusAge;
    
    @NotEmpty
    private Integer plusAge;
    
    @NotEmpty
    private Integer readCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @NotEmpty
    private Boolean isActive;
    
    public MatchingEntity toEntity() {
        return MatchingEntity.builder()
                .writerId(writerId)
                .type(type)
                .title(title)
                .place(place)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .maxMember(maxMember)
                .minusAge(minusAge)
                .plusAge(plusAge)
                .readCount(readCount)
                .isActive(isActive)
                .build();
    }
}