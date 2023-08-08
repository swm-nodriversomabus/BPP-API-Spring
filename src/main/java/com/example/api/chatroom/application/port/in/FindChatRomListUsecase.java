package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;

import java.util.List;

public interface FindChatRomListUsecase {
    List<ChatRoom> chatRoomList(Long userId);
}
