package com.example.api.chat.application.port.out;

import com.example.api.chat.domain.Chat;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetChatListPort {
    List<Chat> getChatList(UUID roomId, Pageable pageable);
}
