package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotNull
    @Min(1L)
    private Long writerId;
    
    @NotNull
    private MatchingTypeEnum type;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String place;
    
    @NotBlank
    private String content;
    
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;
    
    @NotNull
    @Min(1)
    private Integer maxMember;
    
    @NotNull
    @Min(0)
    private Integer minusAge;
    
    @NotNull
    @Min(0)
    private Integer plusAge;
    
    @NotNull
    @Min(0)
    private Integer readCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @NotNull
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