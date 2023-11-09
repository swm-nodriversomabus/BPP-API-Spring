package com.example.api.matching.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
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
@IdClass(LikePK.class)
@Table(name="likes")
public class LikeEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    @Id
    private Long matchingId;
}