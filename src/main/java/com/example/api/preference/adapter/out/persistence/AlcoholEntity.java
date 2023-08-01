package com.example.api.preference.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
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
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}