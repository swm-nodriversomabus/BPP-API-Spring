package com.example.api.chat.config;

import com.example.api.chat.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // STOMP 사용 용도
@RequiredArgsConstructor
public class ChatConfiguration implements WebSocketMessageBrokerConfigurer {
    // WebSocketHandler 생성자 추가
    private final StompHandler stompHandler;

    // end point 추가
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*");
//                .withSockJS();
    }

    // TODO
    // 추후 jwt 인증시 필요 - 인증 절차
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }

    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는데 사용될 메시지 브로커이다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // sub으로 시작되는 요청을 구독한 모든 사용자들에게 메시지를 broadcast
        // queue 는 1대1 전용
        // topic은 1대다 전용
        registry.enableSimpleBroker("/queue","/topic");
        // pub로 시작되네는 메시지는 message-handling methods로 라우팅
        // 즉 브로커 보내준다.
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
