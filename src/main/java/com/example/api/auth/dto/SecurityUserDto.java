package com.example.api.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class SecurityUserDto {
    @NotNull
    private UUID userId;

    private String naverId;

    private String kakaoId;

    private String googleId;

    private String instaId;

    private String appleId;

    @NotNull
    private String role;
}