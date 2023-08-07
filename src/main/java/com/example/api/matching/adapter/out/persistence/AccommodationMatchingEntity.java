package com.example.api.matching.adapter.out.persistence;

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
    private Long matchingId;
    
    @Column
    private Integer price;
    
    @Column(length = 3000)
    private String room;
}