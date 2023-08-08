package com.example.api.chatroom.adapter.in.rest;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;
    private final FindChatRomListUsecase findChatRomListUsecase;
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

    /**
     * TODO 추후에 로그인한 jwt 값으로 회원 정보를 확인할려고함
     * 지금은 임시로 유저 id값을 받자
     * @return
     */
    @GetMapping
    public List<ChatRoom> chatRoomList(@RequestParam Long userId){ //추후 바꾸자함
        return findChatRomListUsecase.chatRoomList(userId);
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
