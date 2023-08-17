package com.example.api.chat.application.port.in;

import com.example.api.chat.domain.Chat;

import java.util.List;
import java.util.UUID;

public interface GetChatListUsecase {
    List<Chat> getChatList(UUID roomId);

}
