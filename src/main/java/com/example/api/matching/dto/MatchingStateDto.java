package com.example.api.matching.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MatchingStateDto {
    @NotEmpty
    private Long matchingId;
    
    @NotEmpty
    private Long userId;
    
    @NotEmpty
    private Boolean complete;
}