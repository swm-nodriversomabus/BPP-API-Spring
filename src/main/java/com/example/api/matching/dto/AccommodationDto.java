package com.example.api.matching.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDto {
    private Long matchingId;
    
    @Min(0)
    private Integer price;
    
    @Min(0)
    private Integer pricePerOne;
    
    private String room;
}