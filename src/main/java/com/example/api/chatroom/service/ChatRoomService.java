package com.example.api.chatroom.service;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.out.SaveChatRoomPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService implements CreateChatRoomUsecase {
    private final SaveChatRoomPort saveChatRoomPort;
    private Map<UUID, ChatRoom> chatRooms;
    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }
    @Override
    public ChatRoom createRoom(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .chatroomName(createChatRoomDto.getChatroomName())
                .isActive(createChatRoomDto.getIsActive())
                .type(createChatRoomDto.getType()).build();
        chatRooms.put(chatRoom.getChatroomId(), chatRoom);
        return chatRoom;
    }

    @Override
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    @Override
    public ChatRoom findById(UUID roomId) {
        return chatRooms.get(roomId);
    }
}
