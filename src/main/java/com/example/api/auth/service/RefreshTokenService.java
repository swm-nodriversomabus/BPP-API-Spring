package com.example.api.auth.service;


import com.example.api.auth.application.port.in.FindRefreshUsecase;
import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.repository.RefreshTokenRepository;
import com.example.api.auth.type.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService implements LogoutUsecase, FindRefreshUsecase {
    private final RefreshTokenRepository repository;

    /**
     * 토큰 저장
     * @param id
     * @param accessToken
     * @param refreshToken
     */
    @Transactional
    public void saveToken(String id, String accessToken, String refreshToken) {
        repository.save(new RefreshToken(id, accessToken, refreshToken));
    }

    /**
     * 토큰 저장 2 - 위랑 다른점은 refresh token차이점
     * @param token
     */
    @Override
    @Transactional
    public void save(RefreshToken token) {
        repository.save(token);
    }

    @Override
    public Optional<RefreshToken> findByAccessToken(String accessToken) {
        return repository.findByAccessToken(accessToken);
    }

    @Override
    @Transactional
    public void removeToken(String accessToken) {
        RefreshToken token = repository.findByAccessToken(accessToken)
                .orElseThrow(IllegalArgumentException::new);
        repository.delete(token); // token 삭제
    }
}
