package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.matching.domain.MatchingApplication;

public interface CreateChatRoomUsecase {
    ChatRoom createRoom(CreateChatRoomDto createChatRoomDto);
    ChatRoom createMatchingChatRoom(MatchingApplication matchingApplication);
}