package com.example.api.auth.handler;

import com.example.api.auth.service.JwtUtilService;
import com.example.api.auth.utils.GeneratedToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtilService jwtUtilService;

    @Value("{main.url}")
    String url;
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
            GeneratedToken token = jwtUtilService.generatedToken(id, role, provider);

            String targetUrl = UriComponentsBuilder.fromUriString(url + "/main")
                    .queryParam("accessToken", token.getAccessToken())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            log.info("success redirecting");

            getRedirectStrategy().sendRedirect(request, response, targetUrl);


        }

    }
}
