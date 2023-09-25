package com.example.api.chat.adapter.in.rest;

import com.example.api.chat.application.port.in.GetChatListUsecase;
import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Chat", description = "Chat API")
public class ChatController {
    //임시용
    private final SendChatUsecase sendChatUsecase;
    private final SubscribeRoomUsecase subscribeRoomUsecase;
    private final GetChatListUsecase getChatListUsecase;
    private final SimpMessagingTemplate simpMessagingTemplate;


    /**
     * 추후에 jwt 인증을 통해 유저 데이터를 불러와 message에 추가할 예정
     * @param roomNumber
     * @param message
     */
    @Operation(summary = "Send message", description = "채팅방에 메시지를 보낸다.")
    @MessageMapping("/chat/{roomNumber}")
    public void message(@DestinationVariable String roomNumber, AddChatDto message){
        log.info("roomNumber : {}", roomNumber);
        sendChatUsecase.send(roomNumber, message);
    }

    /**
     * 구독을 시작할 때 클라이언트가 사용
     * 컨슈머가 없을 시 등록 + 추후에 유저 추가 알림 같은 것을 전달 가능해 보임
     * @param roomId
     */
    @Operation(summary = "Enter chatroom", description = "채팅방에 입장한다.")
    @MessageMapping("/subscribe/{roomId}")
    public void subscribe(@DestinationVariable String roomId){
        subscribeRoomUsecase.subscribe(roomId);
    }

    /**
     * 채팅 내역 불러오기
     * @param roomId
     * @param pageable
     * @return List<Chat>
     */
    @Operation(summary = "Get chat list", description = "채팅 목록을 불러온다.")
    @GetMapping("/chat")
    public List<Chat> getChatList(@RequestParam UUID roomId,@PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC, page = 0, size = 30) Pageable pageable) {
        return getChatListUsecase.getChatList(roomId, pageable);
    }
}