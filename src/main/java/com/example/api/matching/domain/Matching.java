package com.example.api.matching.domain;

import com.example.api.matching.type.MatchingTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matching {
    private Long matchingId;
    private Long writerId;
    private MatchingTypeEnum type;
    private String title;
    private String place;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer maxMember;
    private Integer minusAge;
    private Integer plusAge;
    private Integer readCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}