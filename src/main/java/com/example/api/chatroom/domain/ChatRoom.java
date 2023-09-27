package com.example.api.chatroom.domain;

import com.example.api.chatroom.type.ChatRoomEnum;
import com.example.api.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private UUID chatroomId;
    private ChatRoomEnum type;
    private Long masterId;
    private String chatroomName;
    private Boolean isActive;
    private List<Member> members;
    
    @Builder
    public ChatRoom(UUID chatroomId, ChatRoomEnum type, String chatroomName, Boolean isActive, Long masterId, List<Member> members){
        this.chatroomId = chatroomId;
        this.type = type;
        this.masterId = masterId;
        this.chatroomName = chatroomName;
        this.isActive = isActive;
        this.members = members;
    }
}