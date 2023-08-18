package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchingPreferencePK implements Serializable {
    private Long matchingId;
    private Long preferenceId;
}