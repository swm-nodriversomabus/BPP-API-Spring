package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.application.port.out.SaveChatRoomPort;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatroomPersistenceAdapter implements SaveChatRoomPort {

    @Override
    public void createChatRoom(CreateChatRoomDto chatRoom) {

    }
}
