package com.example.api.preference.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MatchingPreferencePK implements Serializable {
    private Long matchingId;
    private Long preferenceId;
}