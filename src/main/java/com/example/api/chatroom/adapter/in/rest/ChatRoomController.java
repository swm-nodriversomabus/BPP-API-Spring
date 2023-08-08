package com.example.api.chatroom.adapter.in.rest;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.member.application.port.in.AddMemberChatRoomUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;
    /**
     * 채팅방 생성
     * @param createChatRoomDto
     * @return 채팅 방 ID 값
     */
    @PostMapping
    public UUID createChatroom(@RequestBody @Valid CreateChatRoomDto createChatRoomDto){
        ChatRoom ret = createChatRoomUsecase.createRoom(createChatRoomDto);
        return ret.getChatroomId();
    }

//    @PostMapping(value="/{chatRoomId}")
//    public void enterChatRoom(@PathVariable UUID chatRoomId){
//        log.info("chatroom = {}",chatRoomId);
//    }

    @DeleteMapping(value = "/{chatRoomId}")
    public void outChatRoom(@PathVariable UUID chatRoomId){
        log.info("chatroom = {}", chatRoomId);
    }
}
