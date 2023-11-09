package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.application.port.out.FindChatRoomListPort;
import com.example.api.chatroom.application.port.out.RetrieveChatRoomPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatRoomPersistenceAdapter implements CreateChatRoomPort, FindChatRoomListPort, RetrieveChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapperInterface chatRoomMapper;

    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.save(chatRoomMapper.toEntity(chatRoom));
        return chatRoomMapper.toDomain(chatRoomEntity);
    }

    @Override
    public ChatRoom retrieveChatRoom(UUID chatroomId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(chatroomId)
                .orElseThrow(IllegalArgumentException::new);
        return chatRoomMapper.toDomain(chatRoomEntity);
    }

    @Override
    public List<ChatRoomEntity> getChatRoomList(UUID userId, Pageable pageable) {
        Page<ChatRoomEntity> ret = chatRoomRepository.findAllByUserId(pageable, userId);
        if (ret != null && ret.hasContent()) {
            return ret.getContent();
        }
        return new ArrayList<>();
    }
}