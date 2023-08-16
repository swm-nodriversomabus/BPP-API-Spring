package com.example.api.chat.adapter.in.rest;

import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.domain.Message;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    //임시용
    private final SendChatUsecase sendChatUsecase;
    private final SubscribeRoomUsecase subscribeRoomUsecase;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{roomNumber}")
    public void message(@DestinationVariable String roomNumber, Message message){
        log.info("roomNumber : {}", roomNumber);
        sendChatUsecase.send(roomNumber, message);
    }

    @MessageMapping("/subscribe/{roomId}")
    public String asd(@DestinationVariable String roomId){
        subscribeRoomUsecase.subscribe(roomId);
        return "tr";
    }


////
//    @KafkaListener(topics = "{roomId}",groupId = "${random.uuid}"
////            , containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void receieve(Message message, @Header("roomNumber") String roomId) {
//        log.info("topic{}", topicName);
//        this.simpMessagingTemplate.convertAndSend("/topic/channel/" + message.getRoomId(), message);
//    }
}