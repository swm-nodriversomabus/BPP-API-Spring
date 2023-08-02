package com.example.api.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class ChatConfiguration implements WebSocketConfigurer {
    // WebSocketHandler 생성자 추가
    private final WebSocketHandler webSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // endpoint 설정 : /ws/chat
        // ws://localhost:8080/ws/chat 으로 요청이 들어오면 websocket으로 통신
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
