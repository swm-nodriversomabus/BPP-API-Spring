package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FindChatRoomListPort {
    List<ChatRoom> chatRoomList(Pageable pageable, UUID userId);
}