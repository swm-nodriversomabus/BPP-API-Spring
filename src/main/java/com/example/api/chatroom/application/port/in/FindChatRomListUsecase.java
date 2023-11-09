package com.example.api.chatroom.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FindChatRomListUsecase {
    List<ChatRoom> chatRoomList(UUID userId, Pageable pageable);
}