package com.example.api.auth.application.port.in;

public interface LogoutUsecase {
    void removeToken(String accessToken);
}