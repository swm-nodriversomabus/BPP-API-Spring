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
@IdClass(MemberPK.class)
@Table(name="member")
public class MemberEntity {

    @Id
    private Long chatroomId;
    @Id
    private Long userId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date outAt;
}
