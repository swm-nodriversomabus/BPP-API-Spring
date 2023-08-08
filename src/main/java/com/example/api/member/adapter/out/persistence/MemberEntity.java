package com.example.api.member.adapter.out.persistence;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@Getter
@Builder
@IdClass(MemberPK.class)
@Table(name="member")
public class MemberEntity extends BaseEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name="chatroom_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ChatRoomEntity chatroom;

    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name="user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime outAt;

    public void addChatRoom(ChatRoomEntity chatRoom){
        this.chatroom = chatRoom;
    }
}
