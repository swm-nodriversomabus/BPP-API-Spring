package com.example.api.auth.application.port.in;

import com.example.api.auth.type.RefreshToken;

import java.util.Optional;

public interface FindRefreshUsecase {
    // 액세스 코드 찾자
    Optional<RefreshToken> findByAccessToken(String accessToken);
    // 저장용
    void save(RefreshToken token);
}
