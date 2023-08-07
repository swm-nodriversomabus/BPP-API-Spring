package com.example.api.matching.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationMatchingDto {
    private Integer price;
    
    private String room;
}