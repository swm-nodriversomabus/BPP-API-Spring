package com.example.api.matching.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="accommodationMatching")
public class AccommodationMatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;
    
    @Column
    private Integer price;
    
    @Column(length = 3000)
    private String room;
}