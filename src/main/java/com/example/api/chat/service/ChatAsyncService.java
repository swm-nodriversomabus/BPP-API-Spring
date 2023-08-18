package com.example.api.chat.service;


import com.example.api.chat.application.port.out.AddChatPort;
import com.example.api.chat.dto.AddChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatAsyncService {
    private final AddChatPort addChatPort;

    /**
     * 비동기 작업 처리 -> 디비 저장
     * @param addChatDto
     * @return
     */
    @Async
    public CompletableFuture<Void> saveChat(AddChatDto addChatDto){
        try {
            addChatPort.addChat(addChatDto);
        }catch (Exception e){ //혹시라도 문제 생겼을 경우
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(null);
    }

}
