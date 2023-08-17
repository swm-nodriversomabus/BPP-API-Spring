package com.example.api.chat.config;

import com.example.api.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerManager {
        private final SimpMessagingTemplate simpMessagingTemplate;

    //룸에 해당하는 메시지 처리 로직 구현
    public void handleMessageForRoom(String room, Chat chat){
        log.info("message rec from room : {}, message : {}", room, chat);
        this.simpMessagingTemplate.convertAndSend("/topic/channel/" + chat.getRoomId(), chat);

    }
}
