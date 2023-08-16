package com.example.api.chat.config;

import com.example.api.chat.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerManager {
        private final SimpMessagingTemplate simpMessagingTemplate;
//
//    @Override
//    public void receieve(Message message) {
//    }
    //룸에 해당하는 메시지 처리 로직 구현
    public void handleMessageForRoom(String room, Message message){
        log.info("message rec from room : {}, message : {}", room, message);
        this.simpMessagingTemplate.convertAndSend("/topic/channel/" + message.getRoomId(), message);

    }
}
