package com.example.api.chat.service;

import com.example.api.chat.application.port.in.GetChatListUsecase;
import com.example.api.chat.application.port.out.GetChatListPort;
import com.example.api.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatService implements GetChatListUsecase {
    private final GetChatListPort getChatListPort;
    
    @Override
    public List<Chat> getChatList(UUID roomId, Pageable pageable) {
        return getChatListPort.getChatList(roomId, pageable);
    }
}