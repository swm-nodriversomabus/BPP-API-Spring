package com.example.api.chat.service;

import com.example.api.chat.adapter.out.persistence.ChatMapper;
import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.config.KafkaConsumerConfig;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.domain.ChatUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KafkaSendService implements SendChatUsecase {
    private final FindUserPort findUserPort;
    private final ChatAsyncService chatService;
    private final KafkaConsumerConfig kafkaConsumerConfig;
    private final KafkaTemplate<String, Chat> kafkaTemplate;
    private final ChatMapper chatMapper;

    @Override
    @Transactional
    public void send(String roomId, AddChatDto message) {
        Chat sendChat = chatMapper.toDomain(message);
        sendChat.setCreatedAt(LocalDateTime.now());
        final CompletableFuture<Chat> chatResult = chatService.saveChat(message);
        try {
            log.info("kafka send");
            kafkaConsumerConfig.createListenerContainerForRoom(roomId); // 혹시 몰라 컨슈머가 없을 수도 있어 추가
            Chat chat = chatResult.get();
            ChatUser chatUser = sendChat.getSenderId();
            chatUser.setUsername(findUserPort.getByUserId(chatUser.getUserId()).get().getUsername());
            chat.setSenderId(chatUser);
            kafkaTemplate.send(roomId, chat);
            log.info("kafka send FINISH");
        } catch (Exception e) {
            log.error("KafkaSendService::send: chat send error");
            log.error(e.getMessage());
        }
    }
}