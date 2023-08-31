package com.example.api.friend.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Long userId;
    
    @Id
    private Long friendId;
}