package com.example.api.chatroom.domain;

import com.example.api.chatroom.type.ChatRoomEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Builder
    public ChatRoom(UUID chatroomId, ChatRoomEnum type, String chatroomName, Boolean isActive, Long masterId){
        this.chatroomId = chatroomId;
        this.type = type;
        this.masterId = masterId;
        this.chatroomName = chatroomName;
        this.isActive = isActive;
    }

}
