package com.example.api.auth.adapter.in;


import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.application.port.in.FindRefreshUsecase;
import com.example.api.auth.service.JwtUtilService;
import com.example.api.auth.type.RefreshToken;
import com.example.api.auth.type.TokenResponseStatus;
import com.example.api.auth.utils.CookieUtils;
import com.example.api.common.dto.StatusResponseDto;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.user.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

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
        log.info("wow come to logout");
        log.info(access_token);
        logoutUsecase.removeToken(access_token);
        Cookie cookie = new Cookie("access_token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
//        CookieUtils.addCookie(response, "access_token", null, 0);
        log.info("쿠키 삭제");
//
        return ResponseEntity.ok(StatusResponseDto.addStatus(200));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponseStatus> refresh(@CookieValue String access_token, HttpServletResponse response) {
        // accessToken 여부 확인
        Optional<RefreshToken> refreshToken = findRefreshUsecase.findByAccessToken(access_token);

        if (refreshToken.isPresent() && jwtUtilService.verifyToken(refreshToken.get().getRefreshToken())) {
            RefreshToken token = refreshToken.get(); // refresh 토큰 가져오기

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

    @GetMapping("/test")
    public User test(Principal principal){
        User user = AuthenticationUtils.getCurrentUserAuthentication();
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("안녕");
        log.info(user.toString());
        log.info(user.getUserId().toString() );
//        log.info(userDetails.getUsername());
//        log.info(userDetails.getPassword());
        if(user != null){
            log.info(user.getUserId().toString());
        }
        return user;
    }
}