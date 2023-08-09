package com.example.api.chatroom.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindChatRoomListPort {
    List<ChatRoom> chatRoomList(Pageable pageable, Long userId);
}
