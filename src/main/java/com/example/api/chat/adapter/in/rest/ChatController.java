package com.example.api.chat.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.chat.application.port.in.GetChatListUsecase;
import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.multipart.application.port.in.UploadFileUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Chat", description = "Chat API")
public class ChatController {
    private final SendChatUsecase sendChatUsecase;
    private final SubscribeRoomUsecase subscribeRoomUsecase;
    private final GetChatListUsecase getChatListUsecase;
    private final UploadFileUsecase uploadFileUsecase;

    /**
     * 추후에 jwt 인증을 통해 유저 데이터를 불러와 message에 추가할 예정
     * @param roomId (ID)
     * @param message (데이터)
     */
    @Operation(summary = "Send message", description = "채팅방에 메시지를 보낸다.")
    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, AddChatDto message, String contentType, @RequestParam("file") MultipartFile file) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("ChatController::sendMessage: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        message.setSenderId(securityUser.getUserId());
        log.info("roomId : {}", roomId);
        
        if (contentType.equals("image")) {
            if (file.isEmpty()) {
                log.error("ChatController:sendMessage: cannot find a image");
                throw new CustomException(ErrorCodeEnum.IMAGE_NOT_FOUND);
            }
            message.setContent(uploadFileUsecase.uploadFile(file));
        }
        sendChatUsecase.send(roomId, message);
    }

    /**
     * 구독을 시작할 때 클라이언트가 사용
     * 컨슈머가 없을 시 등록 + 추후에 유저 추가 알림 같은 것을 전달 가능해 보임
     * @param roomId (ID)
     */
    @Operation(summary = "Enter chatroom", description = "채팅방에 입장한다.")
    @MessageMapping("/subscribe/{roomId}")
    public void subscribe(@DestinationVariable String roomId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("ChatController::subscribe: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        subscribeRoomUsecase.subscribe(roomId);
    }

    /**
     * 채팅 내역 불러오기
     * @param roomId (ID)
     * @param pageable (데이터)
     * @return List<Chat>
     */
    @Operation(summary = "Get chat list", description = "채팅 목록을 불러온다.")
    @GetMapping("/chat")
    public List<Chat> getChatList(@RequestParam UUID roomId, @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 30) Pageable pageable) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("ChatController::getChatList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return getChatListUsecase.getChatList(roomId, pageable);
    }
}