package com.example.api.chat.adapter.out.persistence;

import com.example.api.chat.application.port.out.AddChatPort;
import com.example.api.chat.dto.AddChatDto;
import com.example.api.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatPersistentAdapter implements AddChatPort {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    @Override
    public void addChat(AddChatDto addChatDto) {
        ChatEntity chatEntity = chatMapper.toEntity(addChatDto);
        chatRepository.save(chatEntity);
    }
}
