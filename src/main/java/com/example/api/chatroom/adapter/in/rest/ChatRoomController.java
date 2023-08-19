package com.example.api.chatroom.adapter.in.rest;

import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
@Tag(name = "ChatRoom", description = "ChatRoom API")
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;
    private final FindChatRomListUsecase findChatRomListUsecase;
    /**
     * 채팅방 생성
     * @param createChatRoomDto
     * @return 채팅 방 ID 값
     */
    @Operation(summary = "Create chatroom", description = "새로운 채팅방을 생성한다.")
    @PostMapping
    public UUID createChatroom(@RequestBody @Valid CreateChatRoomDto createChatRoomDto){
        ChatRoom ret = createChatRoomUsecase.createRoom(createChatRoomDto);
        return ret.getChatroomId();
    }

    /**
     * TODO: 추후에 로그인한 jwt 값으로 회원 정보를 확인하려고 함
     * 지금은 임시로 유저 id값을 받자
     * @return
     */
    @Operation(summary = "Get chatroom list", description = "사용자의 채팅방 목록을 불러온다.")
    @GetMapping
    public List<ChatRoom> chatRoomList(@RequestParam Long userid,@PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC) Pageable pageable){ //추후 바꾸자함
        return findChatRomListUsecase.chatRoomList(pageable, userid);
    }

    @Operation(summary = "Delete chatroom", description = "채팅방을 삭제한다.")
    @DeleteMapping(value = "/{chatRoomId}")
    public void outChatRoom(@PathVariable UUID chatRoomId){
        log.info("chatroom = {}", chatRoomId);
    }
}