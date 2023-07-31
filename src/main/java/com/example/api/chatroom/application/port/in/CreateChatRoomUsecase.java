package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.dto.CreateChatRoomDto;

public interface CreateChatRoomUsecase {
    void createRoom(CreateChatRoomDto createChatRoomDto);
}
