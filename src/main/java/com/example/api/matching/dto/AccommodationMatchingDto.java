package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.AccommodationMatchingEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationMatchingDto {
    private Long accommodationMatchingId;
    
    private Integer price;
    
    private String room;
    
    public AccommodationMatchingEntity toEntity() {
        return AccommodationMatchingEntity.builder()
                .accommodationMatchingId(accommodationMatchingId)
                .price(price)
                .room(room)
                .build();
    }
}