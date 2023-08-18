package com.example.api.chat.service;

import com.example.api.chat.application.port.in.GetChatListUsecase;
import com.example.api.chat.application.port.out.AddChatPort;
import com.example.api.chat.application.port.out.GetChatListPort;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService implements GetChatListUsecase {
    private final GetChatListPort getChatListPort;

    @Override
    public List<Chat> getChatList(UUID roomId, Pageable pageable) {
        return getChatListPort.getChatList(roomId, pageable);
    }


}
