package com.example.api.chat.application.port.in;

import com.example.api.chat.domain.Message;

public interface ReceieveChatUsercase {
    void receieve(Message message);
}
