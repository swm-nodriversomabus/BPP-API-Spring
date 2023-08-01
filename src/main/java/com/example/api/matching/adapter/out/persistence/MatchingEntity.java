package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.type.MatchingType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="matching")
public class MatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @Column(nullable = false)
    private Integer writerId;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchingType type;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(nullable = false, length = 3000)
    private String place;
    
    @Column(nullable = false, length = 6000)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(nullable = false)
    private Integer maxMember;
    
    @Column(nullable = false)
    private Integer minusAge;
    
    @Column(nullable = false)
    private Integer plusAge;
    
    @Column(nullable = false)
    private Integer readCount;
    
    @Column(nullable = false)
    private Integer likeCount;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Column(nullable = false)
    private Boolean isActive;
}