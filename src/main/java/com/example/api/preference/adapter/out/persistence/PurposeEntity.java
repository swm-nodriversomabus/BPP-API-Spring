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
@Table(name="purpose")
public class PurposeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purposeId;
    
    @Column(nullable = false, length = 300)
    private String content;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}