package com.example.api.matching.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikePK implements Serializable {
    private Long userId;
    private Long matchingId;
    
    public LikePK(Long userId, Long matchingId) {
        this.userId = userId;
        this.matchingId = matchingId;
    }
}