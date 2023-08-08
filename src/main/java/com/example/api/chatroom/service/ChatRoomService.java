package com.example.api.chatroom.service;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.application.port.out.FindChatRoomListPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService implements CreateChatRoomUsecase, FindChatRomListUsecase {
    private final CreateChatRoomPort createChatRoomPort;
    private final FindChatRoomListPort findChatRoomListPort;
    @Override
    @Transactional
    public ChatRoom createRoom(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .masterId(createChatRoomDto.getMasterId())
                .chatroomName(createChatRoomDto.getChatroomName())
                .isActive(createChatRoomDto.getIsActive())
                .type(createChatRoomDto.getType()).build();
        return createChatRoomPort.createChatRoom(chatRoom);
    }

    @Override
    public List<ChatRoom> chatRoomList(Long userId) {
        return findChatRoomListPort.chatRoomList(userId);
//        return null;
    }
}
