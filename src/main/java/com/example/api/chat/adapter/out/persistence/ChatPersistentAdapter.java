package com.example.api.chat.adapter.out.persistence;

import com.example.api.chat.application.port.out.AddChatPort;
import com.example.api.chat.application.port.out.GetChatListPort;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import com.example.api.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatPersistentAdapter implements AddChatPort, GetChatListPort {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    @Override
    public void addChat(AddChatDto addChatDto) {
        ChatEntity chatEntity = chatMapper.toEntity(addChatDto);
        chatRepository.save(chatEntity);
    }

    @Override
    public List<Chat> getChatList(UUID roomId, Pageable pageable) {
        Page<ChatEntity> ret = chatRepository.findAllByRoomId_ChatroomId(roomId, pageable);
        log.info("data : {}", ret.getContent());
        if(ret != null && ret.hasContent()){
            return chatMapper.toDomainList(ret.getContent());
        }
        return new ArrayList<>();
    }
}
