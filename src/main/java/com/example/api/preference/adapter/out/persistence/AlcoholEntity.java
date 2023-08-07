package com.example.api.preference.adapter.out.persistence;

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
@Table(name="alcohol")
public class AlcoholEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alcoholId;
    
    @Column(nullable = false, length = 100)
    private String alcoholName;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}