package com.example.api.matching.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum MatchingTypeEnum {
    TravelMate("여행객 매칭"),
    Dining("식사 매칭"),
    Accommodation("숙소 매칭");
    
    private final String matchingType;
}