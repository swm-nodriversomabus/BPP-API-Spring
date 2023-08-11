package com.example.api.matching.domain;

import com.example.api.matching.dto.LikeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LikePK.class)
@Table(name="likes")
public class LikeEntity {
    @Id
    private Long userId;
    
    @Id
    private Long matchingId;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    
    public LikeDto toDto() {
        return LikeDto.builder()
                .userid(userId)
                .matchingId(matchingId)
                .createdAt(createdAt)
                .build();
    }
}