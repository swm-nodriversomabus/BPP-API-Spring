package com.example.api.chatroom.adapter.in.rest;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;

    @PostMapping(value = "/create-chatroom")
    public ChatRoom createChatroom(@RequestBody @Valid CreateChatRoomDto createChatRoomDto){
        System.out.println(createChatRoomDto);
        return createChatRoomUsecase.createRoom(createChatRoomDto);
    }

}
