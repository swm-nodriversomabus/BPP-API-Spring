package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;

public interface CreateChatRoomPort {
    ChatRoom createChatRoom(ChatRoom chatRoom);
}