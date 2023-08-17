package com.example.api.chat.application.port.out;

import com.example.api.chat.dto.AddChatDto;

public interface AddChatPort {
    void addChat(AddChatDto addChatDto);
}
