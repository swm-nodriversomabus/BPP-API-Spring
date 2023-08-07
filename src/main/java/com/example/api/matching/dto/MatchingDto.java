package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.type.MatchingType;
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
    private MatchingType type;
    
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
    
    @NotEmpty
    private Integer likeCount;

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
                .likeCount(likeCount)
                .isActive(isActive)
                .build();
    }
}