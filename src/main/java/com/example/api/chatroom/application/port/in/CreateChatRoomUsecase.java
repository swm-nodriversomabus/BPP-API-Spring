package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;

import java.util.List;
import java.util.UUID;

public interface CreateChatRoomUsecase {
    ChatRoom createRoom(CreateChatRoomDto createChatRoomDto);
    //지울 예정 이 밑들은
    List<ChatRoom> findAllRoom();
    ChatRoom findById(UUID roomId);
}
/**
 *
 * 방을 만들고 -> 들어온다
 *
 */