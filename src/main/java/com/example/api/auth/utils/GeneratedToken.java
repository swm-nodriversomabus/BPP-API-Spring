package com.example.api.auth.utils;


import lombok.Builder;
import lombok.Getter;

@Getter
public class GeneratedToken {
    String accessToken;
    String refreshToken;

    public GeneratedToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
