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
    private UUID masterId;
    private String chatroomName;
    private Boolean isActive;
    private List<UUID> memberIds;
    
    @Builder
    public ChatRoom(UUID chatroomId, ChatRoomEnum type, String chatroomName, Boolean isActive, UUID masterId, List<UUID> memberIds){
        this.chatroomId = chatroomId;
        this.type = type;
        this.masterId = masterId;
        this.chatroomName = chatroomName;
        this.isActive = isActive;
        this.memberIds = memberIds;
    }

    public void setMembers(List<UUID> memberIds) {
        this.memberIds = memberIds;
    }
}