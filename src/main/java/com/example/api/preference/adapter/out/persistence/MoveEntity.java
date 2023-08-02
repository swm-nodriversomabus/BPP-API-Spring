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
@Table(name="move")
public class MoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moveId;
    
    @Column(nullable = false, length = 300)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}