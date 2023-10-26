package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FindChatRoomListPort {
    List<ChatRoomEntity> getChatRoomList(UUID userId, Pageable pageable);
}