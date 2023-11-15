package com.example.api.chat.handler;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.auth.service.JwtUtilService;
import com.example.api.chat.exception.JwtException;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.user.service.UserService;
import jakarta.servlet.http.Cookie;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor{
    private final JwtUtilService jwtUtilService;
    private final UserService userService;
    /**
     * 메시지를 보내기전에 jwt 인증 검사
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        String token = (String) sessionAttributes.get("token");

        if (!jwtUtilService.verifyToken(token)) {
            log.info("JWT 이슈 발생");
            throw new JwtException("Access Token 만료");
        } else {
            // Security Context에 등록할 user 객체 생성
            SecurityUser userinfo = userService.findSocialUser(jwtUtilService.getId(token), jwtUtilService.getProvider(token));
            accessor.setHeader("userId", userinfo.getUserId());
        }
        return message;
    }

    @Override
    public void postSend(@NonNull Message<?> message,@NonNull MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch ((Objects.requireNonNull(accessor.getCommand()))) {
            case CONNECT -> {
                    log.info("session 접속 => {}", sessionId);
           }
            case DISCONNECT -> // websocket으로 disconnect()를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생
                    log.info("세션 아웃 => {}", sessionId);
            default -> {

            }
        }
    }
}
