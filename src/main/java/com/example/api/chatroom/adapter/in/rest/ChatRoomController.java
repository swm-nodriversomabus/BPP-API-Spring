package com.example.api.chatroom.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "ChatRoom", description = "ChatRoom API")
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;
    private final FindChatRomListUsecase findChatRomListUsecase;
    
    /**
     * 채팅방 생성
     * @param createChatRoomDto (데이터)
     * @return UUID
     */
    @Operation(summary = "Create chatroom", description = "새로운 채팅방을 생성한다.")
    @PostMapping("/chatroom")
    public UUID createChatroom(@RequestBody @Valid CreateChatRoomDto createChatRoomDto){
        ChatRoom chatRoom = createChatRoomUsecase.createRoom(createChatRoomDto);
        return chatRoom.getChatroomId();
    }

    /**
     * 채팅방 목록 조회
     * @param pageable (페이지)
     * @return List<ChatRoom>
     */
    @Operation(summary = "Get chatroom list", description = "사용자의 채팅방 목록을 불러온다.")
    @GetMapping("/chatroom")
    public List<ChatRoom> chatRoomList(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) { // 추후 바꾸기
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("ChatRoomController::charRoomList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findChatRomListUsecase.chatRoomList(securityUser.getUserId(), pageable);
    }

    @Operation(summary = "Delete chatroom", description = "채팅방을 삭제한다.")
    @DeleteMapping("/chatroom/{chatRoomId}")
    public void outChatRoom(@PathVariable UUID chatRoomId){
        log.info("chatroom = {}", chatRoomId);
    }
}