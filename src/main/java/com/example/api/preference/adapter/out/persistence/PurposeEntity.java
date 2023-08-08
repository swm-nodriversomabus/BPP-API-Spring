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
@Table(name="purpose")
public class PurposeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purposeId;
    
    @Column(nullable = false, length = 300)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}