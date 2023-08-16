package com.example.api.chat.application.port.in;

import com.example.api.chat.domain.Message;

public interface SendChatUsecase {
    /**
     * 채팅 전송용
     * @param topic topic Name
     * @param message Message
     */
    void send(String roomNumber, Message message);
}
