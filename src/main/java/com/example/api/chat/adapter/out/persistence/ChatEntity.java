package com.example.api.chat.adapter.out.persistence;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
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
@Table(name="chat")
public class ChatEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name="room_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    @Column(nullable = false)
    private ChatRoomEntity roomId;
//    @OneToMany(mappedBy = "chat",fetch = FetchType.LAZY)
//    @Column(nullable = false)
//    private List<ChatRoomEntity> roomId;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false, length = 6000)
    private String content;

    @Column(nullable = false)
    private Boolean image;

    @Column(nullable = false)
    private Integer readCount;


}
