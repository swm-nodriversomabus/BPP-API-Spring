package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;

import java.util.UUID;

public interface RetrieveChatRoomPort {
    ChatRoom retrieveChatRoom(UUID chatroomId);
}
