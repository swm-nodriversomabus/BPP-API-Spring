package com.example.api.matching.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.common.type.ApplicationStateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Long userId;
    
    @Id
    private Long matchingId;
    
    @NotEmpty
    private ApplicationStateEnum state;
    
    @NotEmpty
    private Boolean isActive;
}