package com.example.api.chat.service;

import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSendService implements SendChatUsecase {
    private final KafkaTemplate<String, Chat> kafkaTemplate;
    private final ChatService chatService;
    @Override
    public void send(String roomNumber, AddChatDto message) {
        log.info("send : {}", roomNumber);
        Chat sendChat = message.toMessage();
        final CompletableFuture<Void> chatResult = chatService.saveChat(message);
        chatResult.thenAccept(
                result -> {
                    log.info("끝");
                }
        );
        log.info("kafka send");
        kafkaTemplate.send(roomNumber, sendChat);
        log.info("kafka send FINISH");

    }
}
