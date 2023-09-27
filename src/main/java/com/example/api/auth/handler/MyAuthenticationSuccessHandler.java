package com.example.api.auth.handler;

import com.example.api.auth.service.JwtUtilService;
import com.example.api.auth.utils.CookieUtils;
import com.example.api.auth.utils.GeneratedToken;
import com.example.api.common.utils.CustomBase64Utils;
import com.example.api.social.dto.AddSocialDto;
import com.example.api.social.service.SocialService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtilService jwtUtilService;
    private final SocialService socialService;

    @Value("${main.url}")
    String url;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String id = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");

        // 로그인한 회원 존재 여부
        Integer isExist = oAuth2User.getAttribute("exist");
        if (isExist == null) {
            throw new IllegalAccessError();
        }
        String role = oAuth2User.getAuthorities().stream().
                findFirst()
                .orElseThrow(IllegalAccessError::new) // 존재하지 안흔ㄴ 경우는 에러다
                .getAuthority();

        if (isExist == 3) {
            GeneratedToken token = jwtUtilService.generatedToken(id, role, provider);

            String targetUrl = UriComponentsBuilder.fromUriString(url)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            log.info("success redirecting");
            CookieUtils.addCookie(response, "access_token",token.getAccessToken(), 1000 * 60 * 60);
//            response.addCookie(CookieUtils.addCookie(););
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }else if (isExist == 2) {
            String targetUrl = UriComponentsBuilder.fromUriString(url + "/register/step1")
                    .queryParam("provider", CustomBase64Utils.getBase64EncodeString(provider))
                    .queryParam("socialEmail", CustomBase64Utils.getBase64EncodeString(id))
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            log.info("회원가입 페이지 이동1");
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }else if (isExist == 1){
            log.info("회원가입 안되어 있네");
            AddSocialDto addSocialDto = AddSocialDto.builder()
                    .id(id)
                    .provider(provider)
                    .build();
            socialService.saveSocialInfo(addSocialDto);
            String targetUrl = UriComponentsBuilder.fromUriString(url + "/register/step1")
                    .queryParam("provider", CustomBase64Utils.getBase64EncodeString(provider))
                    .queryParam("socialEmail", CustomBase64Utils.getBase64EncodeString(id))
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            log.info("회원가입 페이지 이동1");
            getRedirectStrategy().sendRedirect(request, response, targetUrl);

        }else{
            log.info("탈퇴한 유저입니다");
            String targetUrl = UriComponentsBuilder.fromUriString(url + "/login")
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            getRedirectStrategy().sendRedirect(request, response, targetUrl);

        }
    }
}

