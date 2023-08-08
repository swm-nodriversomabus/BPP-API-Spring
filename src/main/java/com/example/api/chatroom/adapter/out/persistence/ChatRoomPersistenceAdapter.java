package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.chatroom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomPersistenceAdapter implements CreateChatRoomPort {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;
    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoomEntity ret = chatRoomRepository.save(chatRoomMapper.fromDomainToEntityWithoutId(chatRoom));
        return chatRoomMapper.fromEntityToDomain(ret);
    }
}
