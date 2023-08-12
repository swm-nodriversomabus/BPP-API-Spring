package com.example.api.chatroom.service;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.out.SaveChatRoomPort;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements CreateChatRoomUsecase {
    private final SaveChatRoomPort saveChatRoomPort;
    @Override
    public void createRoom(CreateChatRoomDto createChatRoomDto) {
        
    }
}