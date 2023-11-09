package com.example.api.friend.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FriendPK.class)
@Table(name="friend")
public class FriendEntity extends BaseEntity {
    @Id
    private UUID userId;
    
    @Id
    private UUID friendId;
}