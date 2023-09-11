package com.example.api.matching.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LikePK implements Serializable {
    private Long userId;
    private Long matchingId;
}