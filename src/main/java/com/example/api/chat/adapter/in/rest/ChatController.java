package com.example.api.chat.adapter.in.rest;

import com.example.api.chat.application.port.in.GetChatListUsecase;
import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    //임시용
    private final SendChatUsecase sendChatUsecase;
    private final SubscribeRoomUsecase subscribeRoomUsecase;
    private final GetChatListUsecase getChatListUsecase;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{roomNumber}")
    public void message(@DestinationVariable String roomNumber, AddChatDto message){
        log.info("roomNumber : {}", roomNumber);
        sendChatUsecase.send(roomNumber, message);
    }

    @MessageMapping("/subscribe/{roomId}")
    public void subscribe(@DestinationVariable String roomId){
        subscribeRoomUsecase.subscribe(roomId);
    }

    @GetMapping("/chat")
    public List<Chat> getChatList(@RequestParam UUID roomId,@PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC, page = 0, size = 30) Pageable pageable) {
        return getChatListUsecase.getChatList(roomId, pageable);
    }

}