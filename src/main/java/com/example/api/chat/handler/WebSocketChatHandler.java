package com.example.api.chat.handler;

import com.example.api.chatroom.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatRoomService chatRoomService;
    private final HashMap<String, WebSocketSession> sessionHashMap = new HashMap<>(); // 웹소켓 세션 저장 용
    /**
     * 메시지 수신시 실행
     * 메시지 타입에 따라 handleBinaryMessage or handleTextMessage 실행
     * @param session 세션
     * @param message 메시지
     */
    @Override
    public void handleTextMessage(@Nonnull WebSocketSession session, @Nonnull TextMessage message){
        //메시지 전송
        String msg = message.getPayload();
        for(Map.Entry<String, WebSocketSession> entry : sessionHashMap.entrySet()){
            String key = entry.getKey();
            WebSocketSession wss = sessionHashMap.get(key);
            try{
                wss.sendMessage(new TextMessage(msg));
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }


    }

    /**
     * 웹소켓 연결시 동작
     * @param session 세션
     * @throws Exception 예외처리 필요
     */
    @Override
    public void afterConnectionEstablished(@Nonnull WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("소켓 연결 {}", session.getId());
        sessionHashMap.put(session.getId(), session);
    }

    /**
     * 웹소켓 종료시 동작
     * @param session 세션
     * @param status 상태
     * @throws Exception 예외처리 필요
     */
    @Override
    public void afterConnectionClosed(@Nonnull WebSocketSession session, @Nonnull CloseStatus status) throws Exception {
        sessionHashMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
