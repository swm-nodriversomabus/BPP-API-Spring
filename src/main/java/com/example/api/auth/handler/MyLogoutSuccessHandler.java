package com.example.api.auth.handler;

import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.utils.CookieUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private final ObjectMapper objectMapper;
    private final LogoutUsecase logoutUsecase;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //로그 아웃 성공시 ok 보내자
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                if(cookieName.equals("access_token") && !cookie.getValue().isEmpty()){
                    String accessToken = cookie.getValue();
                    logoutUsecase.removeToken(accessToken);
                    CookieUtils.addCookie(response, cookieName, null, 0);
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
