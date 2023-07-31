package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.type.ChatRoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="chatroom")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatRoomType type;

    @Column(nullable = false, length = 300)
    private String chatroomName;


    @Column(nullable = false)
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
