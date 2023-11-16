package com.example.api.chat.application.port.out;

import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;

public interface AddChatPort {
    Chat addChat(AddChatDto addChatDto);
}