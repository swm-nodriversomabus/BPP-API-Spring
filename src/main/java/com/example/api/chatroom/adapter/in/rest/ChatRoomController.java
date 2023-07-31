package com.example.api.chatroom.adapter.in.rest;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;

    @PostMapping(value = "/create-chatroom")
    public void createChatroom(@RequestParam CreateChatRoomDto createChatRoomDto){
        createChatRoomUsecase.createRoom(createChatRoomDto);
    }

}
