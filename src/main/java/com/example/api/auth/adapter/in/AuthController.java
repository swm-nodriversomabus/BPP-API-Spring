package com.example.api.auth.adapter.in;


import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.application.port.in.FindRefreshUsecase;
import com.example.api.auth.service.JwtUtilService;
import com.example.api.auth.type.RefreshToken;
import com.example.api.auth.type.TokenResponseStatus;
import com.example.api.auth.utils.CookieUtils;
import com.example.api.common.dto.StatusResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RefreshScope
public class AuthController {
    private final JwtUtilService jwtUtilService;
    private final LogoutUsecase logoutUsecase;
    private final FindRefreshUsecase findRefreshUsecase;

    @PostMapping("/auth/logout")
    public ResponseEntity<StatusResponseDto> logout(@CookieValue String access_token, HttpServletResponse response) {
        logoutUsecase.removeToken(access_token);

        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(StatusResponseDto.addStatus(200));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponseStatus> refresh(@CookieValue String access_token, HttpServletResponse response) {

        // accessToken 여부 확인
        Optional<RefreshToken> refreshToken = findRefreshUsecase.findByAccessToken(access_token);

        if (refreshToken.isPresent() && jwtUtilService.verifyToken(refreshToken.get().getRefreshToken())) {
            RefreshToken token = refreshToken.get(); // refresh토큰 가져오기

            String newAccessToken = jwtUtilService.generateAccessToken(token.getId(),
                    jwtUtilService.getRole(token.getRefreshToken()),
                    jwtUtilService.getProvider(token.getRefreshToken()));

            token.updateAccessToken(newAccessToken);
            findRefreshUsecase.save(token);
            log.info(newAccessToken);
            CookieUtils.addCookie(response, "access_token",newAccessToken, 1000 * 60 * 60);

            return ResponseEntity.ok(TokenResponseStatus.addStatus(200,null));
        }
        CookieUtils.addCookie(response, "access_token",null, 1000 * 60 * 60);

        return ResponseEntity.badRequest().body(TokenResponseStatus.addStatus(400,null));
    }
}
