package com.example.api.auth.service;


import com.example.api.auth.application.port.in.LogoutUsecase;
import com.example.api.auth.repository.RefreshTokenRepository;
import com.example.api.auth.type.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService implements LogoutUsecase {
    private final RefreshTokenRepository repository;

    @Transactional
    public void saveToken(String id, String accessToken, String refreshToken) {
        repository.save(new RefreshToken(id, accessToken, refreshToken));
    }



    @Override
    @Transactional
    public void removeToken(String accessToken) {
        RefreshToken token = repository.findByAccessToken(accessToken)
                .orElseThrow(IllegalArgumentException::new);
        repository.delete(token); // token 삭제
    }
}
