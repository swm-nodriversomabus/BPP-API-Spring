package com.example.api.chat.application.port.in;

import com.example.api.chat.domain.Chat;

public interface ReceieveChatUsecase {
    void receieve(Chat chat);
}
