package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;

public interface CreateChatRoomUsecase {
    ChatRoom createRoom(CreateChatRoomDto createChatRoomDto);
}