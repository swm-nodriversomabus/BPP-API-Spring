package com.example.api.matching.domain;

import com.example.api.matching.dto.MatchingDto;
import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="matching")
public class MatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @Column(nullable = false)
    private Long writerId;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchingTypeEnum type;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(nullable = false, length = 3000)
    private String place;
    
    @Column(nullable = false, length = 6000)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;
    
    @Column(nullable = false)
    private Integer maxMember;
    
    @Column(nullable = false)
    private Integer minusAge;
    
    @Column(nullable = false)
    private Integer plusAge;
    
    @Column(nullable = false)
    private Integer readCount;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    public MatchingDto toDto() {
        return MatchingDto.builder()
                .matchingId(matchingId)
                .writerId(writerId)
                .type(type)
                .title(title)
                .place(place)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .maxMember(maxMember)
                .minusAge(minusAge)
                .plusAge(plusAge)
                .readCount(readCount)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .isActive(isActive)
                .build();
    }
}