package com.example.api.matching.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="matchingState")
public class MatchingStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingStateId;
    
    @Column(nullable = false)
    private Integer matchingId;
    
    @Column(nullable = false)
    private Integer userId;
    
    @Column(nullable = false)
    private Boolean complete;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}