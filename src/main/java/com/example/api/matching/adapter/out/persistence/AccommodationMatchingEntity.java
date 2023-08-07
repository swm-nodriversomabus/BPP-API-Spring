package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.dto.AccommodationMatchingDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accommodationMatching")
public class AccommodationMatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationMatchingId;
    
    @Column
    private Integer price;
    
    @Column(length = 3000)
    private String room;
    
    public AccommodationMatchingDto toDto() {
        return AccommodationMatchingDto.builder()
                .price(price)
                .room(room)
                .build();
    }
}