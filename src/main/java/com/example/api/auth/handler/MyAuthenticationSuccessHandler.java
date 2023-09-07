package com.example.api.auth.handler;

import com.example.api.auth.utils.GeneratedToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String id = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");

        // 로그인한 회원 존재 여부
        boolean isExist = oAuth2User.getAttribute("exist");

        String role = oAuth2User.getAuthorities().stream().
                findFirst()
                .orElseThrow(IllegalAccessError::new) // 존재하지 안흔ㄴ 경우는 에러다
                .getAuthority();

        if (isExist) {

        }

    }
}
