package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.type.ChatRoomEnum;
import com.example.api.common.entity.BaseEntity;
import com.example.api.member.adapter.out.persistence.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="chatroom")
public class ChatRoomEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID chatroomId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatRoomEnum type;

    @Column(nullable = false, length = 300)
    private String chatroomName;

    @Column(nullable = false)
    private Long masterId;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "chatroom",fetch = FetchType.LAZY)
    private List<MemberEntity> members;

    // 멤버 추가
    public void addMember(MemberEntity member){
        this.members.add(member);
        member.addChatRoom(this);
    }

//    @OneToMany(mappedBy = "")
}
