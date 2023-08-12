package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.dto.CreateChatRoomDto;

public interface SaveChatRoomPort {
    void createChatRoom(CreateChatRoomDto chatRoom);
}