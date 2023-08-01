package com.example.api.chat.adapter.in.rest;

import com.example.api.chatroom.type.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    //임시용
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    @RequestMapping("/chat")
    public String Home(){
        return "wow chat server";
    }


    @MessageMapping("/hello")
    public void message(Message message){
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(),message);
    }
}
