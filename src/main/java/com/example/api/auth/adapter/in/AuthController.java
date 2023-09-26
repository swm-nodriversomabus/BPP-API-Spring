package com.example.api.auth.adapter.in;


import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.application.port.in.FindRefreshUsecase;
import com.example.api.auth.service.JwtUtilService;
import com.example.api.auth.type.RefreshToken;
import com.example.api.auth.type.TokenResponseStatus;
import com.example.api.common.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    @Value("${test}")
    private String test;


    @GetMapping("/auth/test")
    public String test(){
        return test;
    }
    @PostMapping("/auth/logout")
    public ResponseEntity<StatusResponseDto> logout(@RequestHeader("Authorization") final String accessToken) {
        logoutUsecase.removeToken(accessToken);
        return ResponseEntity.ok(StatusResponseDto.addStatus(200));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponseStatus> refresh(@RequestHeader("Authorization") final String accessToken) {

        // accessToken 여부 확인
        Optional<RefreshToken> refreshToken = findRefreshUsecase.findByAccessToken(accessToken);

        if (refreshToken.isPresent() && jwtUtilService.verifyToken(refreshToken.get().getRefreshToken())) {
            RefreshToken token = refreshToken.get(); // refresh토큰 가져오기

            String newAccessToken = jwtUtilService.generateAccessToken(token.getId(),
                    jwtUtilService.getRole(token.getRefreshToken()),
                    jwtUtilService.getProvider(token.getRefreshToken()));

            token.updateAccessToken(newAccessToken);
            findRefreshUsecase.save(token);

            return ResponseEntity.ok(TokenResponseStatus.addStatus(200, newAccessToken));
        }
        return ResponseEntity.badRequest().body(TokenResponseStatus.addStatus(400, null));
    }
}
