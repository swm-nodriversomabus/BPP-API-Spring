package com.example.api.matching.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum MatchingType {
    TravelMate("여행객 매칭"),
    Dining("식사 매칭"),
    Accommodation("숙소 매칭");
    
    private final String matchingType;
}