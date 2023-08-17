package com.example.api.chat.adapter.in.rest;

import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.dto.AddChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    public void message(@DestinationVariable String roomNumber, AddChatDto message){
        log.info("roomNumber : {}", roomNumber);
        sendChatUsecase.send(roomNumber, message);
    }

    @MessageMapping("/subscribe/{roomId}")
    public String asd(@DestinationVariable String roomId){
        subscribeRoomUsecase.subscribe(roomId);
        return "tr";
    }

}