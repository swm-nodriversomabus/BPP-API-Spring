package com.example.api.matching.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private Long matchingId;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Boolean complete;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}