package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.AccommodationMatchingEntity;
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
    
    public AccommodationMatchingEntity toEntity() {
        return AccommodationMatchingEntity.builder()
                .accommodationMatchingId(accommodationMatchingId)
                .price(price)
                .room(room)
                .build();
    }
}