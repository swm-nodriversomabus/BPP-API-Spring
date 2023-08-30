package com.example.api.matching.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchingApplicationPK implements Serializable {
    private Long userId;
    private Long matchingId;
    
    public MatchingApplicationPK(Long userId, Long matchingId) {
        this.userId = userId;
        this.matchingId = matchingId;
    }
}