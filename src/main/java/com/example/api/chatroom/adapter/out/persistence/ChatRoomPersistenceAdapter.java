package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.application.port.out.FindChatRoomListPort;
import com.example.api.chatroom.application.port.out.RetrieveChatRoomPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.repository.ChatRoomRepository;
import com.example.api.member.repository.MemberRepository;
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
    private final ChatRoomMapper chatRoomMapper;
    private final MemberRepository memberRepository;

    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.save(chatRoomMapper.fromDomainToEntityWithoutId(chatRoom));
        return chatRoomMapper.fromEntityToDomain(chatRoomEntity);
    }

    @Override
    public ChatRoom retrieveChatRoom(UUID chatroomId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(chatroomId)
                .orElseThrow(IllegalArgumentException::new);
        return chatRoomMapper.fromEntityToDomain(chatRoomEntity);
    }

    @Override
    public List<ChatRoom> chatRoomList(Pageable pageable, Long userId) {
        Page<ChatRoomEntity> ret = chatRoomRepository.findAllByUserId(pageable, userId);
        if(ret != null && ret.hasContent()) {
            return chatRoomMapper.fromEntityListToDomain(ret.getContent());
        }
        return new ArrayList<>();
    }
}