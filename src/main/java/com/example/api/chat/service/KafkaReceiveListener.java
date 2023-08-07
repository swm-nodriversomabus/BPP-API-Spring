package com.example.api.chat.service;

import com.example.api.chat.application.port.in.ReceieveChatUsercase;
import com.example.api.chat.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiveListener implements ReceieveChatUsercase {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @KafkaListener(id="main-listener", topics = "chatting")
    public void receieve(Message message) {
        log.info("message receieve");
        this.simpMessagingTemplate.convertAndSend("/topic/channel/" + message.getRoomId(), message);
    }
}
