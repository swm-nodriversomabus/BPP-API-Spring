package com.example.api.chat.config;

import com.example.api.chat.handler.StompHandler;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;

import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker // STOMP 사용 용도
@RequiredArgsConstructor
public class StompConfiguration implements WebSocketMessageBrokerConfigurer {
    // WebSocketHandler 생성자 추가
    private final StompHandler stompHandler;

    // end point 추가
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS()
                .setInterceptors(httpSessHandshakeInterceptor())
        ;
    }

    // TODO
    // 추후 jwt 인증시 필요 - 인증 절차m
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

    @Bean
    public HandshakeInterceptor httpSessHandshakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                if (request instanceof ServletServerHttpRequest){
                    ServletServerHttpRequest servletServerRequest = (ServletServerHttpRequest) request;
                    HttpServletRequest servletRequest = servletServerRequest.getServletRequest();
                    Cookie token = WebUtils.getCookie(servletRequest, "access_token");
                    if (token != null){
                        attributes.put("token", token.getValue());
                    } else{
                        throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
                    }
                }
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

            }
        };
    }

    // 64 KB 이상의 데이터 전송을 위해 사용
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(160 * 64 * 1024);
        registry.setSendTimeLimit(100 * 10000);
        registry.setSendBufferSizeLimit(3 * 512 * 1024);
    }
}
