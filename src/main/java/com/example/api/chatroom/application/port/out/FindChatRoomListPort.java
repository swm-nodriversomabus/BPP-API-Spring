package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;

import java.util.List;

public interface FindChatRoomListPort {
    List<ChatRoom> chatRoomList(Long userId);
}
