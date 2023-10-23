package com.example.api.matching.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MatchingApplicationPK implements Serializable {
    private UUID userId;
    private Long matchingId;
}