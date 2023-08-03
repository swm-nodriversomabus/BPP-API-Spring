package com.example.api.chat.adapter.in.rest;

import com.example.api.chatroom.type.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    //임시용
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    @MessageMapping("/message")
    public void message(Message message){
        log.info("message transfer");
        simpMessageSendingOperations.convertAndSend("/topic/channel/" + message.getRoomId(), message);
    }
}
