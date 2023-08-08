package com.example.api.friend.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="friend")
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;
    
    @Column(nullable = false)
    private Integer userId;
    
    @Column(nullable = false)
    private Integer targetUserId;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}