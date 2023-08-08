package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;

import java.util.List;
import java.util.UUID;

public interface CreateChatRoomUsecase {
    ChatRoom createRoom(CreateChatRoomDto createChatRoomDto);
}
