package com.example.api.matching.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.common.type.ApplicationStateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MatchingApplicationPK.class)
@Table(name="matchingApplication")
public class MatchingApplicationEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    @Id
    private Long matchingId;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStateEnum state;
    
    @Column(nullable = false)
    private Boolean isActive;
}