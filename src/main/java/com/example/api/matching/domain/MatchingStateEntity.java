package com.example.api.matching.domain;

import com.example.api.matching.dto.MatchingStateDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    
    public MatchingStateDto toDto() {
        return MatchingStateDto.builder()
                .matchingStateId(matchingStateId)
                .matchingId(matchingId)
                .userId(userId)
                .complete(complete)
                .createdAt(createdAt)
                .build();
    }
}