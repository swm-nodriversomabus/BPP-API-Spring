package com.example.api.chatroom.domain;

import com.example.api.chatroom.type.ChatRoomType;
import lombok.Builder;

public class ChatRoom {
    private Long chatroomId;
    private ChatRoomType type;
    private String chatroomName;
    private Boolean isActive;
    @Builder
    public ChatRoom(Long chatroomId, ChatRoomType type, String chatroomName, Boolean isActive){
        this.chatroomId = chatroomId;
        this.type = type;
        this.chatroomName = chatroomName;
        this.isActive = isActive;
    }


}
