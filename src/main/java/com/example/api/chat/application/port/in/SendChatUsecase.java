package com.example.api.chat.application.port.in;

import com.example.api.chat.dto.AddChatDto;

public interface SendChatUsecase {
    /**
     * 채팅 전송용
     * @param roomNumber topic Name
     * @param message Message
     */
    void send(String roomNumber, AddChatDto message);
}
