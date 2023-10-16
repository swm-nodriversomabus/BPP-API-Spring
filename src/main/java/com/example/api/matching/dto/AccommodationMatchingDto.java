package com.example.api.matching.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationMatchingDto {
    private Long accommodationMatchingId;
    
    @Min(0)
    private Integer price;
    
    private String room;
}