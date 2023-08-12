package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.type.ChatRoomType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="chatroom")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    @Column(nullable = false, length = 300)
    private String chatroomName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatRoomType type;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isActive;
}