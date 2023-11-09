package com.example.api.matching.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accommodationMatching")
public class AccommodationMatchingEntity {
    @Id
    private Long matchingId;
    
    @Column
    private Integer price;
    
    @Column(length = 3000)
    private String room;
}