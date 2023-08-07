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

    /**
     * 채팅방 생성
     * @param createChatRoomDto
     * @return 채팅 방 ID 값
     */
    @PostMapping(value = "/chatroom")
    public ChatRoom createChatroom(@RequestBody @Valid CreateChatRoomDto createChatRoomDto){
        return createChatRoomUsecase.createRoom(createChatRoomDto);
    }

}
