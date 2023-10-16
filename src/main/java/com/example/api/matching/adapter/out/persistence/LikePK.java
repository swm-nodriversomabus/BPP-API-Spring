package com.example.api.matching.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LikePK implements Serializable {
    private UUID userId;
    private Long matchingId;
}