package com.example.api.chatroom.domain;

import com.example.api.chatroom.type.ChatRoomType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class ChatRoom {
    private UUID chatroomId;
    private ChatRoomType type;
    private String chatroomName;
    private Boolean isActive;
    @Builder
    public ChatRoom(ChatRoomType type, String chatroomName, Boolean isActive){
        this.chatroomId = UUID.randomUUID();
        this.type = type;
        this.chatroomName = chatroomName;
        this.isActive = isActive;
    }
//    public static ChatRoom create(ChatRoomType type){
//        ChatRoom room = new ChatRoom();
//        room.chatroomId = UUID.randomUUID();
//        room.type = type;
//        room.isActive = true;
//        room.chatroomName = "";
//        return room;
//    }

}
