package com.example.api.chat.handler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class StompHandler implements ChannelInterceptor{
    @Override
    public void postSend(@NonNull Message<?> message,@NonNull MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch ((Objects.requireNonNull(accessor.getCommand()))) {
            case CONNECT -> log.info("세션 접속 => {}", sessionId);
            case DISCONNECT -> // websocket으로 disconnect()를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생
                    log.info("세션 아웃 => {}", sessionId);
            default -> {

            }
        }
    }
}
