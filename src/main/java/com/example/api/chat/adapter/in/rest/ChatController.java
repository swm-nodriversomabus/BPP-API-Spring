package com.example.api.chat.adapter.in.rest;

import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    //임시용
    private final SendChatUsecase sendChatUsecase;
    @Value("${kafka.my.push.topic.name}")
    private String topicName;
    @MessageMapping("/message")
    public void message(Message message){
        log.info("message transfer");
        sendChatUsecase.send("chatting", message);
    }
}